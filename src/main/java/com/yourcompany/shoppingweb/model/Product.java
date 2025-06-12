package com.yourcompany.shoppingweb.model;

// 商品实体类
public class Product {
    private int id; // 商品ID
    private String name; // 商品名称
    private double price; // 价格
    private int categoryId; // 分类ID
    private String description; // 商品描述
    private boolean isHot; // 是否热门
    private int stock; // 库存

    public Product() {}

    public Product(int id, String name, double price, int categoryId, String description, boolean isHot, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.isHot = isHot;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
} 