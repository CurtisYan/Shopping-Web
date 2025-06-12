<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/style.css">
    <style>
        body { background: #f7f7f7; }
        .login-card {
            max-width: 350px;
            margin: 80px auto;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.10);
            padding: 36px 32px 28px 32px;
        }
        .login-card h2 { text-align: center; color: #388e3c; margin-bottom: 24px; }
        .login-card input[type=text], .login-card input[type=password] {
            width: 100%; padding: 10px 12px; margin: 10px 0 18px 0;
            border: 1px solid #ccc; border-radius: 6px; font-size: 1em;
        }
        .login-card .btn {
            width: 100%; background: #388e3c; color: #fff; border: none;
            border-radius: 6px; padding: 10px 0; font-size: 1.1em; cursor: pointer;
            margin-bottom: 10px;
        }
        .login-card .btn:hover { background: #2e7031; }
        .login-card .link { text-align: right; display: block; margin-top: 8px; }
        .login-card .msg { color: #e53935; text-align: center; margin-bottom: 10px; }
    </style>
</head>
<body>
<div class="login-card">
    <h2>用户登录</h2>
    <form action="<%=request.getContextPath()%>/login" method="post">
        <input type="text" name="username" placeholder="用户名" required/>
        <input type="password" name="password" placeholder="密码" required/>
        <button type="submit" class="btn">登录</button>
    </form>
    <% String msg = (String)request.getAttribute("msg");
       if(msg != null) { %>
        <div class="msg"><%=msg%></div>
    <% } %>
    <a href="<%=request.getContextPath()%>/jsp/register.jsp" class="link">没有账号？去注册</a>
</div>
</body>
</html> 