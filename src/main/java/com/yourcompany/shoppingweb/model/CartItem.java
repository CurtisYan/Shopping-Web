package com.yourcompany.shoppingweb.model;

// 购物车项，表示购物车中的一个商品和数量
public class CartItem {
    private Product product; // 商品对象
    private int quantity;   // 购买数量

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // 获取该项总价
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
} 