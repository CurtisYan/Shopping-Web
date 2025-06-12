<%@ include file="/jsp/navbar.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.yourcompany.shoppingweb.model.User" %>
<%
    // 获取Session中的用户信息
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%-- 首页，显示欢迎信息 --%>
<h2>欢迎，<%= user != null ? user.getUsername() : "游客" %>！</h2>
<p><a href="<%=request.getContextPath()%>/logout">退出登录</a></p>
</body>
</html> 