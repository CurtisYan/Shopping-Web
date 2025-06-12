package com.yourcompany.shoppingweb.servlet;

import com.yourcompany.shoppingweb.dao.OrderDao;
import com.yourcompany.shoppingweb.model.CartItem;
import com.yourcompany.shoppingweb.model.Order;
import com.yourcompany.shoppingweb.model.OrderItem;
import com.yourcompany.shoppingweb.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 订单Servlet，处理提交订单和查询我的订单
public class OrderServlet extends HttpServlet {
    private final OrderDao orderDao = new OrderDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 提交订单
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (user == null || cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderDate(new Date());
        order.setStatus("已支付");
        double total = 0;
        List<OrderItem> items = new ArrayList<>();
        for (CartItem ci : cart) {
            OrderItem item = new OrderItem();
            item.setProduct(ci.getProduct());
            item.setQuantity(ci.getQuantity());
            item.setPrice(ci.getProduct().getPrice());
            items.add(item);
            total += ci.getTotalPrice();
        }
        order.setItems(items);
        order.setTotalAmount(total);
        int orderId = orderDao.saveOrder(order);
        if (orderId > 0) {
            session.removeAttribute("cart");
            req.setAttribute("orderId", orderId);
            req.getRequestDispatcher("/jsp/order_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "下单失败，可能库存不足");
            req.getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询我的订单
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        List<Order> orders = orderDao.findByUserId(user.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/jsp/order_list.jsp").forward(req, resp);
    }
} 