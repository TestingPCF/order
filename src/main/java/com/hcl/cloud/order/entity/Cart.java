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
	 * @param cartIdObj
	 *            Cart Id.
	 * @param subTotalObj
	 *            Sub total.
	 * @param userIdObj
	 *            User Id.
	 * @param cartItemsObj
	 *            cart items.
	 */
	public Cart(final String cartIdObj, final BigDecimal subTotalObj, final String userIdObj, final List<ShoppingItem> cartItemsObj) {
		this.cartId = cartIdObj;
		this.subTotal = subTotalObj;
		this.userId = userIdObj;
		this.cartItems = cartItemsObj;
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
	 * @param cartIdObj
	 *            cart id.
	 */
	public final void setCartId(final String cartIdObj) {
		this.cartId = cartIdObj;
	}

	/**
	 * getSubTotal.
	 *
	 * @return subTotal total
	 */
	public final BigDecimal getSubTotal() {
		return subTotal;
	}

	/**
	 * setSubTotal.
	 *
	 * @param subTotalObj
	 *            sub total.
	 */
	public final void setSubTotal(final BigDecimal subTotalObj) {
		this.subTotal = subTotalObj;
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
	 * @param userIdObj
	 *            User Id.
	 */
	public final void setUserId(final String userIdObj) {
		this.userId = userIdObj;
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
	 * @param cartItemsObj
	 *            cart Items.
	 */
	public final void setCartItems(final List<ShoppingItem> cartItemsObj) {
		this.cartItems = cartItemsObj;
	}
}
