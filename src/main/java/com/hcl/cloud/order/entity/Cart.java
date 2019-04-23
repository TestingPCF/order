/*
 * Copyright (c) 2019.
 */
package com.hcl.cloud.order.entity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * This is a representational entity for Cart.
 *
 * @author shikhar.a || ankit-kumar
 */
public class Cart {

    /**
     * cartId
     */
    private String cartId;

    /**
     * subTotal
     */
    private BigDecimal subTotal;

    /**
     * userId.
     */
    @NotNull(message = "Please provide user Id.")
    private String userId;

    /**
     * cartItems.
     */
    private List<ShoppingItem> cartItems;

    /**
     * Default Cart.
     */
    public Cart() {
    }

    /**
     * Cart constructor.
     * @param cartId
     * @param subTotal
     * @param userId
     * @param cartItems
     */
    public Cart(String cartId, BigDecimal subTotal, String userId, List<ShoppingItem> cartItems) {
        this.cartId = cartId;
        this.subTotal = subTotal;
        this.userId = userId;
        this.cartItems = cartItems;
    }

    /**
     * Getter Cart id
     * @return
     */
    public String getCartId() {
        return cartId;
    }

    /**
     * Setter cart id.
     * @param cartId
     */
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    /**
     * getSubTotal.
     * @return
     */
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
