<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/navbar.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yourcompany.shoppingweb.model.Order" %>
<%@ page import="com.yourcompany.shoppingweb.model.OrderItem" %>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
<head>
    <title>我的订单</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/style.css">
</head>
<body>
<div class="container">
    <h2>我的订单</h2>
    <% if(orders == null || orders.isEmpty()) { %>
        <p>暂无订单。</p>
    <% } else { %>
        <% for(Order order : orders) { %>
        <div class="order-card">
            <div style="margin-bottom:8px;">
                <b>订单编号：</b><%=order.getId()%>
                <span style="margin-left:24px;">下单时间：<%=order.getOrderDate()%></span>
                <span style="margin-left:24px;">状态：<%=order.getStatus()%></span>
            </div>
            <table class="cart-table">
                <tr>
                    <th>商品</th><th>单价</th><th>数量</th><th>小计</th>
                </tr>
                <% for(OrderItem item : order.getItems()) { %>
                <tr>
                    <td><%=item.getProduct().getName()%></td>
                    <td>￥<%=item.getPrice()%></td>
                    <td><%=item.getQuantity()%></td>
                    <td>￥<%=item.getPrice() * item.getQuantity()%></td>
                </tr>
                <% } %>
                <tr>
                    <td colspan="3" style="text-align:right;font-weight:bold;">总计：</td>
                    <td style="color:#388e3c;font-size:1.1em;">￥<%=order.getTotalAmount()%></td>
                </tr>
            </table>
        </div>
        <% } %>
    <% } %>
</div>
</body>
</html> 