<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>下单成功</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/style.css">
</head>
<body>
<div class="container">
    <h2>订单提交成功！</h2>
    <p>您的订单编号：<b><%=request.getAttribute("orderId")%></b></p>
    <a href="<%=request.getContextPath()%>/product-list" class="add-btn">继续购物</a>
    <a href="<%=request.getContextPath()%>/order-list" class="add-btn">查看我的订单</a>
</div>
</body>
</html> 