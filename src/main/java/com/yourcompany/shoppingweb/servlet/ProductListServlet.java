package com.yourcompany.shoppingweb.servlet;

import com.yourcompany.shoppingweb.dao.CategoryDao;
import com.yourcompany.shoppingweb.dao.ProductDao;
import com.yourcompany.shoppingweb.model.Category;
import com.yourcompany.shoppingweb.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 商品列表Servlet，负责展示商品和分类
public class ProductListServlet extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();
    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryIdStr = req.getParameter("categoryId");
        List<Category> categories = categoryDao.findAll();
        List<Product> products;
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdStr);
            products = productDao.findByCategory(categoryId);
        } else {
            products = productDao.findAll();
        }
        req.setAttribute("categories", categories);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/product_list.jsp").forward(req, resp);
    }
} 