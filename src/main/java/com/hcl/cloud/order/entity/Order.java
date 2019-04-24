package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is entity class for Order.
 *
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "TORDER")
@Access(value = AccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings({"PMD.HiddenField"})
public class Order {

 /**
  * Constant PRICE.
  */
 private static final int PRICE = 100;

 /**
  * orderId.
  */
 @Id
 @Column(name = "order_id", unique = true, nullable = false)
 @GeneratedValue(strategy = GenerationType.SEQUENCE,
   generator = "order_sequence")
 @SequenceGenerator(name = "order_sequence",
   sequenceName = "ORDER_SEQ")
 private Long orderId;

 /**
  * orderStatus.
  */
 private String orderStatus;

 /**
  * orderDate.
  */
 private Date orderDate;

 /**
  * deliveryDate.
  */
 private Date deliveryDate;

 /**
  * userEmail.
  */
 @NotNull
 private String userEmail;

 /**
  * paymentMode.
  */
 @NotNull
 private String paymentMode;

 /**
  * shippingAddress.
  */
 private String shippingAddress;

 /**
  * orderTotal.
  */
 private BigDecimal orderTotal;

 /**
  * shoppingItems.
  */
 @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
 private List<ShoppingItem> shoppingItems;

 /**
  * Order.
  */
 public Order() {
 }

 /**
  * Order.
  *
  * @param orderStatusObj
  *            Order Status
  * @param orderDateObj
  *            Order Date
  * @param deliveryDateObj
  *            Delivery Date
  * @param userEmailObj
  *            User Email
  * @param paymentModeObj
  *            Payment Maode
  * @param shippingAddressObj
  *            Shipping Address
  * @param orderTotalObj
  *            Order Total
  * @param shoppingItemsObj
  *            Shopping Items
  */
 public Order(final String orderStatusObj,
     final Date orderDateObj,
     final Date deliveryDateObj,
     final String userEmailObj,
     final String paymentModeObj,
     final String shippingAddressObj,
     final BigDecimal orderTotalObj,
     final List<ShoppingItem> shoppingItemsObj) {
  this.orderStatus = orderStatusObj;
  this.orderDate = orderDateObj;
  this.deliveryDate = deliveryDateObj;
  this.userEmail = userEmailObj;
  this.paymentMode = paymentModeObj;
  this.shippingAddress = shippingAddressObj;
  this.orderTotal = orderTotalObj;
  this.shoppingItems = shoppingItemsObj;
 }

 /**
  * Order.
  *
  * @param orderIdObj
  *            Order Id
  * @param orderStatusObj
  *            Order Status
  * @param orderDateObj
  *            Order Date
  * @param deliveryDateObj
  *            Deliver Date
  * @param userEmailObj
  *            User Email
  * @param paymentModeObj
  *            Payment Mode
  * @param shippingAddressObj
  *            Shipping Address
  * @param orderTotalObj
  *            Order Total
  * @param shoppingItemsObj
  *            Shopping Items
  */
 public Order(final Long orderIdObj,
     final String orderStatusObj,
     final Date orderDateObj,
     final Date deliveryDateObj,
     final @NotNull String userEmailObj,
     final @NotNull String paymentModeObj,
     final String shippingAddressObj,
     final BigDecimal orderTotalObj,
     final List<ShoppingItem> shoppingItemsObj) {
  this.orderId = orderIdObj;
  this.orderStatus = orderStatusObj;
  this.orderDate = orderDateObj;
  this.deliveryDate = deliveryDateObj;
  this.userEmail = userEmailObj;
  this.paymentMode = paymentModeObj;
  this.shippingAddress = shippingAddressObj;
  this.orderTotal = orderTotalObj;
  this.shoppingItems = shoppingItemsObj;
 }

 /**
  * This will return a sample order object. [For testing purpose only]
  *
  * @return Order Order object
  */
 public static Order getSampleOrder() {
  Order order = new Order();
  ShoppingItem item = new ShoppingItem("Iphone_16GB",
    1,
    new BigDecimal(PRICE), new BigDecimal(PRICE),
    new BigDecimal(PRICE));
  item.setOrder(order);
  ArrayList items = new ArrayList();
  items.add(item);
  order.setOrderDate(new Date());
  order.setOrderStatus("IN_PROGRESS");
  order.setOrderTotal(new BigDecimal("PRICE"));
  order.setUserEmail("shikhar.a@hcl.com");
  order.setShippingAddress("Noida, UP");
  order.setPaymentMode("CASH");
  order.setShoppingItems(new ArrayList<ShoppingItem>(items));

  return order;
 }

 /**
  * getOrderId.
  *
  * @return orderId Order Id
  */
 public final Long getOrderId() {
  return orderId;
 }

 /**
  * setOrderId.
  *
  * @param orderIdObj
  *            Order Id
  */
 public final void setOrderId(final Long orderIdObj) {
  this.orderId = orderIdObj;
 }

 /**
  * getOrderStatus.
  *
  * @return orderStatus status
  */
 public final String getOrderStatus() {
  return orderStatus;
 }

 /**
  * setOrderStatus.
  *
  * @param orderStatusObj
  *            status
  */
 public final void setOrderStatus(final String orderStatusObj) {
  this.orderStatus = orderStatusObj;
 }

 /**
  * getOrderDate.
  *
  * @return orderDate order date
  */
 public final Date getOrderDate() {
  return orderDate;
 }

 /**
  * setOrderDate.
  *
  * @param orderDateObj
  *            order date
  */
 public final void setOrderDate(final Date orderDateObj) {
  this.orderDate = orderDateObj;
 }

 /**
  * getDeliveryDate.
  *
  * @return deliveryDate date
  */
 public final Date getDeliveryDate() {
  return deliveryDate;
 }

 /**
  * setDeliveryDate.
  *
  * @param deliveryDateObj
  *            date
  */
 public final void setDeliveryDate(final Date deliveryDateObj) {
  this.deliveryDate = deliveryDateObj;
 }

 /**
  * getUserEmail.
  *
  * @return userEmail email
  */
 public final String getUserEmail() {
  return userEmail;
 }

 /**
  * setUserEmail.
  *
  * @param userEmailObj
  *            email
  */
 public final void setUserEmail(final String userEmailObj) {
  this.userEmail = userEmailObj;
 }

 /**
  * getPaymentMode.
  *
  * @return paymentMode payment mode
  */
 public final String getPaymentMode() {
  return paymentMode;
 }

 /**
  * setPaymentMode.
  *
  * @param paymentModeObj
  *            payment mode
  */
 public final void setPaymentMode(final String paymentModeObj) {
  this.paymentMode = paymentModeObj;
 }

 /**
  * getShippingAddress.
  *
  * @return shippingAddress address
  */
 public final String getShippingAddress() {
  return shippingAddress;
 }

 /**
  * setShippingAddress.
  *
  * @param shippingAddressObj
  *            address
  */
 public final void setShippingAddress(final String shippingAddressObj) {
  this.shippingAddress = shippingAddressObj;
 }

 /**
  * getOrderTotal.
  *
  * @return orderTotal total
  */
 public final BigDecimal getOrderTotal() {
  return orderTotal;
 }

 /**
  * setOrderTotal.
  *
  * @param orderTotalObj
  *            total
  */
 public final void setOrderTotal(final BigDecimal orderTotalObj) {
  this.orderTotal = orderTotalObj;
 }

 /**
  * getShoppingItems.
  *
  * @return shoppingItems items
  */
 public final List<ShoppingItem> getShoppingItems() {
  return shoppingItems;
 }

 /**
  * setShoppingItems.
  *
  * @param shoppingItemsObj
  *            items
  */
 public final void setShoppingItems(
   final List<ShoppingItem> shoppingItemsObj) {
  this.shoppingItems = shoppingItemsObj;
 }
}
