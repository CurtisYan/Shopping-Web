package com.yourcompany.shoppingweb.model;

// 商品分类实体类
public class Category {
    private int id; // 分类ID
    private String name; // 分类名称

    public Category() {}

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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
} 