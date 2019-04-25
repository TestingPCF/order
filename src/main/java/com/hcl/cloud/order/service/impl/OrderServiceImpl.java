package com.hcl.cloud.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.order.client.RestClient;
import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.dto.CartItem;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.ShoppingItem;
import com.hcl.cloud.order.repository.OrderRepositorySql;
import com.hcl.cloud.order.service.OrderService;
import com.hcl.cloud.order.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
  *
  * @param order
  * @param authToken
  * @return
  * @throws IOException
  */
 public final ResponseEntity<Object> checkout(final Order order,
                               final String authToken) throws IOException {
  ResponseEntity<Object> cartResponse =
          RestClient.getResponseFromMS(OrderConstant.INVERNTORY_CART,
                  null ,authToken);

   CartResponse response = ResponseUtil.getCartResponse(cartResponse);

  logger.info(OrderConstant.START
          + OrderConstant.ORDER_CREATING_INFO
          + order.getUserEmail());
  ResponseEntity<Object> inventoryResponse =
          RestClient.getResponseFromMS(OrderConstant.INVERNTORY_READ,
                  response ,authToken);
  if(inventoryResponse.getStatusCode()== HttpStatus.EXPECTATION_FAILED){
   return ResponseUtil.getResponseEntity(HttpStatus.BAD_REQUEST,
           OrderConstant.OUT_OF_STOCK, null);
  }
  order.setPaymentMode("CASH");
  order.setOrderStatus("IN-PROGRESS");
  order.setUserEmail(response.getData().getUserId());
  order.setOrderTotal(response.getData().getSubTotal());
  order.setOrderDate(new Date());

  List<ShoppingItem> items = new ArrayList<>();
  order.setShoppingItems(items);
  for(CartItem item : response.getData().getCartItems()){
   ShoppingItem shoppingItem = new ShoppingItem();
   shoppingItem.setOrder(order);
   shoppingItem.setListPrice(item.getListrice());
   shoppingItem.setSalePrice(item.getSalePrice());
   shoppingItem.setQuantity(item.getQuantity());
   shoppingItem.setSkuCode(item.getItemCode());
   shoppingItem.setTotalPrice(item.getSalePrice()
           .multiply(new BigDecimal(item.getQuantity())));
   items.add(shoppingItem);
  }

  logger.info(OrderConstant.INPROGRES
          + OrderConstant.ORDER_CREATING_INFO
          + order.getUserEmail());
  Order persistedOrder = orderRepositorySql.save(order);
  ResponseEntity<Object> udpateInventoryResponse =
          RestClient.getResponseFromMS(OrderConstant.INVERNTORY_UPDATE,
                  response ,authToken);
  logger.info(OrderConstant.COMPLETED
          + OrderConstant.ORDER_CREATING_INFO
          + order.getUserEmail());
  return ResponseUtil.getResponseEntity(HttpStatus.CREATED,
          OrderConstant.ORDER_CREATED + persistedOrder
                  .getOrderId(), null);
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
