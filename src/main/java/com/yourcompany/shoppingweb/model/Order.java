package com.yourcompany.shoppingweb.model;

import java.util.Date;
import java.util.List;

// 订单实体类
public class Order {
    private int id; // 订单ID
    private int userId; // 用户ID
    private Date orderDate; // 下单时间
    private double totalAmount; // 总金额
    private String status; // 订单状态
    private List<OrderItem> items; // 订单明细

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
} 