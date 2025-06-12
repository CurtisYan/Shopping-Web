package com.yourcompany.shoppingweb.servlet;

import com.yourcompany.shoppingweb.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理用户注册请求
public class RegisterServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String msg = null;
        try {
            boolean success = userDao.register(username, password);
            if (success) {
                req.setAttribute("msg", "注册成功，请登录");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                return;
            } else {
                msg = "用户名已存在";
            }
        } catch (Exception e) {
            msg = "注册失败：" + e.getMessage();
        }
        req.setAttribute("msg", msg);
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }
} 