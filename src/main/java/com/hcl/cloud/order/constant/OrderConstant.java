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
  * Constant for ORDER_FETCHING_INFO.
  */
 public static final String ORDER_FETCHING_INFO = "Order is being fetched "
         + "for"
         + " order id ";

 /**
  * Constant for ORDER_UPDATING_INFO.
  */
 public static final String ORDER_UPDATING_INFO = "Order is being updated "
         + "for"
         + " order id ";

 /**
  * Constant for ORDER_CREATING_INFO.
  */
 public static final String ORDER_CREATING_INFO = "Order is being created "
         + "for"
         + " user id ";

 /**
  * Constant for ORDER_GETALL_INFO.
  */
 public static final String ORDER_GETALL_INFO = "Orders are being fetched";

 /**
  * Constant for START.
  */
 public static final String START = "[START] ";

 /**
  * Constant for INPROGRES.
  */
 public static final String INPROGRES = "[IN-PROGRESS] ";

 /**
  * Constant for COMPLETED.
  */
 public static final String COMPLETED = "[COMPLETED] ";

 /**
  * Constant for ERROR.
  */
 public static final String ERROR = "[ERROR] ";

 /**
  * Constructor.
  * @param obj Object
  */
 private OrderConstant(final Object obj) {
  System.out.println(obj);
 }
}
