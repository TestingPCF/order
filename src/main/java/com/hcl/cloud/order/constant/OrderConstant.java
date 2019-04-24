package com.hcl.cloud.order.constant;

/**
 * This a final class having constant values.
 */
public final class OrderConstant {
 /**
  * Constant for ORDER_CREATED.
  */
 public static final String ORDER_CREATED = "Your order has"
   + " been placed, your order Id is:: ";

 /**
  * Constant for ORDER_SUCCESS.
  */
 public static final String ORDER_SUCCESS = "Your request"
   + " processed successfully.";

 /**
  * Constant for ORDER_UPDATED.
  */
 public static final String ORDER_UPDATED = "Your order"
   + " updated successfully.";

 /**
  * Constant for ORDER_FAILED.
  */
 public static final String ORDER_FAILED = "Something "
   + "went wrong with this order.";

 /**
  * Constructor.
  * @param obj Object
  */
 private OrderConstant(final Object obj) {

 }
}
