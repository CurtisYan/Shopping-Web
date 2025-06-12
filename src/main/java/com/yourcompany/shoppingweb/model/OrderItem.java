package com.yourcompany.shoppingweb.model;

// 订单明细实体类
public class OrderItem {
    private int id; // 明细ID
    private int orderId; // 订单ID
    private Product product; // 商品对象
    private int quantity; // 购买数量
    private double price; // 单价

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
} 