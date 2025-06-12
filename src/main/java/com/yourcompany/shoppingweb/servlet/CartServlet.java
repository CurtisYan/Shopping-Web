package com.yourcompany.shoppingweb.servlet;

import com.yourcompany.shoppingweb.dao.ProductDao;
import com.yourcompany.shoppingweb.model.CartItem;
import com.yourcompany.shoppingweb.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 购物车Servlet，处理添加、修改、删除、展示
public class CartServlet extends HttpServlet {
    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 展示购物车页面
        req.getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        // 未登录则跳转登录页
        if (session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }
        List<CartItem> cart = getCart(session);

        if ("add".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Product product = productDao.findAll().stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
            if (product != null) {
                boolean found = false;
                for (CartItem item : cart) {
                    if (item.getProduct().getId() == productId) {
                        // 已在购物车，增加数量
                        item.setQuantity(item.getQuantity() + quantity);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    cart.add(new CartItem(product, quantity));
                }
            }
            session.setAttribute("cart", cart);
            req.setAttribute("msg", "已加入购物车");
            req.getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
            return;
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            for (CartItem item : cart) {
                if (item.getProduct().getId() == productId) {
                    item.setQuantity(quantity);
                    break;
                }
            }
            session.setAttribute("cart", cart);
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        } else if ("delete".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            Iterator<CartItem> it = cart.iterator();
            while (it.hasNext()) {
                if (it.next().getProduct().getId() == productId) {
                    it.remove();
                    break;
                }
            }
            session.setAttribute("cart", cart);
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        // 默认跳转购物车
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    // 获取购物车（Session中）
    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
} 