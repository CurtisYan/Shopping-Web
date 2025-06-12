package com.yourcompany.shoppingweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// 身份认证过滤器，拦截未登录用户
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        // 检查Session中是否有user对象
        if (session == null || session.getAttribute("user") == null) {
            // 没有登录，跳转到登录页
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }
        // 已登录，放行
        chain.doFilter(request, response);
    }
} 