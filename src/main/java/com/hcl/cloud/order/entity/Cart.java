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
     * cartId.
     */
    private String cartId;

    /**
     * subTotal.
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
     *
     * @param cartId    Cart Id.
     * @param subTotal  Sub total.
     * @param userId    User Id.
     * @param cartItems cart items.
     */
    public Cart(final String cartId, final BigDecimal subTotal, final String userId
            , final List<ShoppingItem> cartItems) {
        this.cartId = cartId;
        this.subTotal = subTotal;
        this.userId = userId;
        this.cartItems = cartItems;
    }

    /**
     * Getter Cart id.
     *
     * @return cartId
     */
    public final String getCartId() {
        return cartId;
    }

    /**
     * Setter cart id.
     *
     * @param cartId cart id.
     */
    public final void setCartId(final String cartId) {
        this.cartId = cartId;
    }

    /**
     * getSubTotal.
     *
     * @return subTotal
     */
    public final BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * setSubTotal.
     *
     * @param subTotal sub total.
     */
    public final void setSubTotal(final BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * getUserId.
     *
     * @return userId
     */
    public final String getUserId() {
        return userId;
    }

    /**
     * setUserId.
     *
     * @param userId User Id.
     */
    public final void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * getCartItems.
     *
     * @return cartItems
     */
    public final List<ShoppingItem> getCartItems() {
        return cartItems;
    }

    /**
     * setCartItems.
     *
     * @param cartItems cart Items.
     */
    public final void setCartItems(final List<ShoppingItem> cartItems) {
        this.cartItems = cartItems;
    }
}
