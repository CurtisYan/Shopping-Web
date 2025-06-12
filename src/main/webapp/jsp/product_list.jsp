<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yourcompany.shoppingweb.model.Category" %>
<%@ page import="com.yourcompany.shoppingweb.model.Product" %>
<%@ include file="/jsp/navbar.jsp" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/style.css">
</head>
<body>
<div class="container">
    <h2>商品浏览</h2>
    <form method="get" action="<%=request.getContextPath()%>/product-list">
        <label>商品分类：</label>
        <select name="categoryId" onchange="this.form.submit()">
            <option value="">全部</option>
            <% for(Category c : categories) { %>
                <option value="<%=c.getId()%>" <%=request.getParameter("categoryId")!=null && request.getParameter("categoryId").equals(String.valueOf(c.getId()))?"selected":""%>><%=c.getName()%></option>
            <% } %>
        </select>
    </form>
    <div class="product-list">
        <% for(Product p : products) { %>
        <div class="product-card">
            <div class="product-info">
                <h3><%=p.getName()%></h3>
                <p>￥<%=p.getPrice()%></p>
                <p>库存：<%=p.getStock()%></p>
                <% if(p.isHot()) { %><span class="hot">热门</span><% } %>
                <p class="desc"><%=p.getDescription()%></p>
            </div>
            <form method="post" action="<%=request.getContextPath()%>/cart">
                <input type="hidden" name="action" value="add"/>
                <input type="hidden" name="productId" value="<%=p.getId()%>"/>
                <input type="number" name="quantity" value="1" min="1" max="<%=p.getStock()%>" class="qty-input"/>
                <button type="submit" class="add-btn">加入购物车</button>
            </form>
        </div>
        <% } %>
    </div>
</div>
</body>
</html> 