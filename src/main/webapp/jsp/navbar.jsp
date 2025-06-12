<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.yourcompany.shoppingweb.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    boolean loggedIn = user != null;
    String username = loggedIn ? user.getUsername() : "未登录";
%>
<%-- 顶部导航栏 --%>
<div class="navbar">
    <div class="nav-left">
        <a href="<%=request.getContextPath()%>/product-list" class="logo">购物网</a>
        <a href="<%=request.getContextPath()%>/product-list">商品主页</a>
        <a href="<%=request.getContextPath()%>/cart">购物车</a>
    </div>
    <div class="nav-right">
        <div class="dropdown">
            <span class="user-icon"><%=username%> ▼</span>
            <div class="dropdown-content">
                <% if (loggedIn) { %>
                    <a href="<%=request.getContextPath()%>/order-list">我的订单</a>
                    <a href="<%=request.getContextPath()%>/logout">退出登录</a>
                <% } else { %>
                    <a href="<%=request.getContextPath()%>/jsp/login.jsp">去登录</a>
                <% } %>
            </div>
        </div>
    </div>
</div> 