package com.hcl.cloud.order.service.impl;

import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.repository.OrderRepositorySql;
import com.hcl.cloud.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is the implementation class of OrderService interface. This has the
 * method definition of methods which will be used for Order related operations
 * such as insert, update, get.
 *
 * @author shikhar.a || ankit-kumar
 * @see com.hcl.cloud.order.service.OrderService
 */
@Service
public class OrderServiceImpl implements OrderService {

 /**
  * Logger.
  */
 private static Logger logger = LoggerFactory
         .getLogger(OrderServiceImpl.class);

 /**
  * This is an autowired object of our order repository , responsible to
  * interact with mongoDB.
  */
 @Autowired
 private OrderRepositorySql orderRepositorySql;

 /**
  * This method definition is for saving a new order.
  *
  * @param cart
  *            object to be saved
  * @return Saved order object
  */
 @Override
 public final Order checkout(final Cart cart) {
  Order order = Order.getSampleOrder();
  logger.info(OrderConstant.INPROGRES
          + OrderConstant.ORDER_CREATING_INFO
          + cart.getUserId());
  Order persistedOrder = orderRepositorySql.save(order);
  return persistedOrder;
 }

 /**
  * This method definition is for updating an order. We will allow update
  * operation only for status and delivery date.
  *
  * @param order
  *            object to be saved
  * @return Saved order object
  */
 @Override
 public final Order updateOrder(final Order order) {
  logger.info(OrderConstant.INPROGRES
          + OrderConstant.ORDER_UPDATING_INFO
          + order.getOrderId());
  Order persistedOrder = orderRepositorySql.save(order);
  return persistedOrder;
 }

 /**
  * This method definition is for retrieving the details of a particular
  * order.
  *
  * @param orderId
  * @return Order object
  */
 @Override
 public final Order getOrder(final Long orderId) {
  logger.info(OrderConstant.INPROGRES
          + OrderConstant.ORDER_FETCHING_INFO
          + orderId);
  Order persistedOrder = orderRepositorySql.getOne(orderId);
  return persistedOrder;
 }

 /**
  * This method definition is for retrieving a list of all the orders.
  *
  * @return List of orders
  */
 @Override
 public final List<Order> getAllOrders() {
  logger.info(OrderConstant.ERROR
          + OrderConstant.ORDER_GETALL_INFO);
  return orderRepositorySql.findAll();
 }
}
