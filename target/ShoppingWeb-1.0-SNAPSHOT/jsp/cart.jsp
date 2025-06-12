<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yourcompany.shoppingweb.model.CartItem" %>
<%@ include file="/jsp/navbar.jsp" %>
<%
    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
    if (cart == null) cart = new java.util.ArrayList<>();
    double total = 0;
    for (CartItem item : cart) {
        total += item.getTotalPrice();
    }
    String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/style.css">
</head>
<body>
<div class="container">
    <h2>我的购物车</h2>
    <a href="<%=request.getContextPath()%>/product-list" class="add-btn" style="margin-bottom:18px;display:inline-block;">返回继续购物</a>
    <% if(msg != null) { %>
        <p style="color:green"><%=msg%></p>
    <% } %>
    <% if(cart.isEmpty()) { %>
        <p>购物车空空如也，快去选购商品吧！</p>
        <a href="<%=request.getContextPath()%>/product-list">返回商品页</a>
    <% } else { %>
    <table class="cart-table">
        <tr>
            <th>商品</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
            <th>操作</th>
        </tr>
        <% for(CartItem item : cart) { %>
        <tr>
            <td><%=item.getProduct().getName()%></td>
            <td>￥<%=item.getProduct().getPrice()%></td>
            <td>
                <form method="post" action="<%=request.getContextPath()%>/cart">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="productId" value="<%=item.getProduct().getId()%>"/>
                    <input type="number" name="quantity" value="<%=item.getQuantity()%>" min="1" max="<%=item.getProduct().getStock()%>" class="qty-input"/>
                    <button type="submit" class="add-btn">修改</button>
                </form>
            </td>
            <td>￥<%=item.getTotalPrice()%></td>
            <td>
                <form method="post" action="<%=request.getContextPath()%>/cart" style="display:inline;">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="productId" value="<%=item.getProduct().getId()%>"/>
                    <button type="submit" class="add-btn" style="background:#e53935;">删除</button>
                </form>
            </td>
        </tr>
        <% } %>
        <tr>
            <td colspan="3" style="text-align:right;font-weight:bold;">总计：</td>
            <td colspan="2" style="color:#388e3c;font-size:1.1em;">￥<%=total%></td>
        </tr>
    </table>
    <form method="post" action="<%=request.getContextPath()%>/order">
        <button type="submit" class="add-btn" style="margin-top:18px;">提交订单</button>
    </form>
    <% } %>
</div>
</body>
</html> 