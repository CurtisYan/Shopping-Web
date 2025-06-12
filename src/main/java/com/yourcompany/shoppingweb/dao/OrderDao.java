package com.yourcompany.shoppingweb.dao;

import com.yourcompany.shoppingweb.model.Order;
import com.yourcompany.shoppingweb.model.OrderItem;
import com.yourcompany.shoppingweb.model.Product;
import com.yourcompany.shoppingweb.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 订单数据访问对象
public class OrderDao {
    // 保存订单和明细，返回订单ID
    public int saveOrder(Order order) {
        String orderSql = "INSERT INTO orders(user_id, order_date, total_amount, status) VALUES (?, NOW(), ?, ?)";
        String itemSql = "INSERT INTO order_item(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        String updateStockSql = "UPDATE product SET stock = stock - ? WHERE id = ? AND stock >= ?";
        int orderId = 0;
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getUserId());
                orderStmt.setDouble(2, order.getTotalAmount());
                orderStmt.setString(3, order.getStatus());
                orderStmt.executeUpdate();
                ResultSet rs = orderStmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }
            // 保存订单明细并扣减库存
            for (OrderItem item : order.getItems()) {
                try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                    itemStmt.setInt(1, orderId);
                    itemStmt.setInt(2, item.getProduct().getId());
                    itemStmt.setInt(3, item.getQuantity());
                    itemStmt.setDouble(4, item.getPrice());
                    itemStmt.executeUpdate();
                }
                try (PreparedStatement stockStmt = conn.prepareStatement(updateStockSql)) {
                    stockStmt.setInt(1, item.getQuantity());
                    stockStmt.setInt(2, item.getProduct().getId());
                    stockStmt.setInt(3, item.getQuantity());
                    int updated = stockStmt.executeUpdate();
                    if (updated == 0) throw new SQLException("库存不足");
                }
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            orderId = 0;
        }
        return orderId;
    }

    // 查询用户所有订单
    public List<Order> findByUserId(int userId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setItems(findItemsByOrderId(order.getId()));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 查询订单明细
    public List<OrderItem> findItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.*, p.name, p.price as product_price FROM order_item oi JOIN product p ON oi.product_id = p.id WHERE oi.order_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(orderId);
                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("product_price"));
                item.setProduct(p);
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
} 