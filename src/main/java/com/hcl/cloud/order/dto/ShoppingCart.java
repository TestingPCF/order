package com.hcl.cloud.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for ShoppingCart. This class includes getter
 * and setter methods
 * for properties of the ShoppingCart.
 * @author shikhar.a
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCart {

 /**
  * cartId.
  */
 private Long id;

 /**
  * subTotal.
  */
 private BigDecimal subTotal = new BigDecimal(0.00);

 /**
  * userId.
  */
 private String userId;

 /**
  * userId.
  */
 private List<CartItem> cartItems = new ArrayList<>();

 /**
  * getCartId.
  * @return id id
  */
 public final Long getId() {
  return id;
 }

 /**
  * setCartId.
  * @param idParam Cart Id
  */
 public final void setId(final Long idParam) {
  this.id = idParam;
 }

 /**
  * getSubTotal.
  * @return subTotal Subtotal
  */
 public final BigDecimal getSubTotal() {
  this.subTotal = calculateSubtotal();
  return subTotal;
 }

 /**
  * setSubTotal.
  * @param subTotalParam subTotal
  */
 public final void setSubTotal(final BigDecimal subTotalParam) {
  this.subTotal = subTotalParam;
 }

 /**
  * getUserId.
  * @return userId userId
  */
 public final String getUserId() {
  return userId;
 }

 /**
  * setUserId.
  * @param userIdParam userIdParam
  */
 public final void setUserId(final String userIdParam) {
  this.userId = userIdParam;
 }

 /**
  * getCartItems.
  * @return cartItems cartItems
  */
 public final List<CartItem> getCartItems() {
  return cartItems;
 }

 /**
  * setCartItems.
  * @param cartItemsParam cartItemsParam
  */
 public final void setCartItems(final List<CartItem> cartItemsParam) {
  this.cartItems = cartItemsParam;
 }

 /**
  * calculateSubtotal.
  * @return total total
  */
 private BigDecimal calculateSubtotal() {
  BigDecimal total = new BigDecimal(0.00);
  for (CartItem cartItem : cartItems) {
   total = total.add(cartItem.getSalePrice()
     .multiply(new BigDecimal(cartItem.getQuantity())));
  }
  return total;
 }
}
