package com.hcl.cloud.order.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * This is a representational entity for Cart.
 * @author shikhar.a || ankit-kumar
 */
public class Cart {

    private String cartId;
    private BigDecimal subTotal;
    private String userId;
    private List<ShoppingItem> cartItems;

    public Cart() {
    }

    public Cart(String cartId, BigDecimal subTotal, String userId, List<ShoppingItem> cartItems) {
        this.cartId = cartId;
        this.subTotal = subTotal;
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ShoppingItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingItem> cartItems) {
        this.cartItems = cartItems;
    }
}
