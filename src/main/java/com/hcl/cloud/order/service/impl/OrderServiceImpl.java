package com.hcl.cloud.order.service.impl;

import com.hcl.cloud.order.client.RestClient;
import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.dto.CartItem;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.ShoppingItem;
import com.hcl.cloud.order.exception.BadRequestException;
import com.hcl.cloud.order.repository.OrderRepositorySql;
import com.hcl.cloud.order.service.OrderService;
import com.hcl.cloud.order.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
  * checkout process.
  * @param order Order
  * @param authToken Auth Token
  * @return response entity
  * @throws IOException Exception
  */
 public final ResponseEntity<Object> checkout(final Order order,
                                              final String authToken) throws IOException {
  ResponseEntity<Object> cartResponse = null;
  try {
   cartResponse = RestClient.getResponseFromMS(OrderConstant.INVERNTORY_CART,
           null, authToken);
  } catch (Exception e) {
   logger.info(OrderConstant.ERROR
           + OrderConstant.ORDER_CREATING_INFO);
   if (e instanceof HttpClientErrorException) {
    HttpStatus status = ((HttpClientErrorException) e).getStatusCode();
    if (status == HttpStatus.NOT_FOUND) {
     return ResponseUtil.getResponseEntity(HttpStatus.SERVICE_UNAVAILABLE,
             OrderConstant.CART_CONNECTION_ERROR,
             null);
    }
    return ResponseUtil.getResponseEntity(status, e.getMessage(), null);
   } else {
    return ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
            OrderConstant.CART_CONNECTION_ERROR, null);
   }
  }

  logger.info(OrderConstant.START
          + OrderConstant.ORDER_CREATING_INFO
          + order.getUserEmail());

  CartResponse response = ResponseUtil.getCartResponse(cartResponse);

  logger.info(OrderConstant.INPROGRES
          + OrderConstant.ORDER_CREATING_INFO
          + order.getUserEmail());

  ResponseEntity<Object> inventoryResponse = null;

  try {
   inventoryResponse = RestClient.getResponseFromMS(OrderConstant
                   .INVERNTORY_READ,
           response, authToken);
  } catch (Exception e) {
   logger.info(OrderConstant.ERROR
           + OrderConstant.ORDER_CREATING_INFO
           + order.getUserEmail());
   if (e instanceof HttpClientErrorException) {
    HttpStatus status = ((HttpClientErrorException) e).getStatusCode();
    if (status == HttpStatus.NOT_FOUND) {
     return ResponseUtil.getResponseEntity(HttpStatus.SERVICE_UNAVAILABLE,
             OrderConstant.INVENTORY_CONNECTION_ERROR,
             null);
    }
    return ResponseUtil.getResponseEntity(status, e.getMessage(), null);
   } else {
    return ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
            OrderConstant.INVENTORY_CONNECTION_ERROR, null);
   }
  }
  if (inventoryResponse.getStatusCode() == HttpStatus.EXPECTATION_FAILED) {
   logger.info(OrderConstant.ERROR
           + OrderConstant.ORDER_CREATING_INFO
           + order.getUserEmail());
   return ResponseUtil.getResponseEntity(HttpStatus.BAD_REQUEST,
           OrderConstant.OUT_OF_STOCK, null);
  }

  order.setPaymentMode(OrderConstant.CASH);
  order.setOrderStatus(OrderConstant.ORDER_IN_PROGRESS);
  order.setUserEmail(response.getData().getUserId());
  order.setOrderTotal(response.getData().getSubTotal());
  order.setOrderDate(new Date());

  List<ShoppingItem> items = new ArrayList<>();
  order.setShoppingItems(items);
  for (CartItem item : response.getData().getCartItems()) {
   ShoppingItem shoppingItem = new ShoppingItem();
   shoppingItem.setOrder(order);
   shoppingItem.setListPrice(item.getListPrice());
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
                  response, authToken);
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
  String status = order.getOrderStatus();
  Date deliveryDate = order.getDeliveryDate();
  Order orderEntity = this.getOrder(order.getOrderId());
  if(orderEntity == null) {
   throw new BadRequestException(OrderConstant.ORDER_NOT_FOUND_ERROR);
  }
  if(status != null && !status.isEmpty()){
   logger.info(OrderConstant.INPROGRES
           + OrderConstant.ORDER_UPDATING_INFO
           + orderEntity.getOrderId() + " updating status to " + status);
   orderEntity.setOrderStatus(status);
  }
  if(deliveryDate != null){
   logger.info(OrderConstant.INPROGRES
           + OrderConstant.ORDER_UPDATING_INFO
           + order.getOrderId() + " updating delivery date to " + deliveryDate);
   orderEntity.setDeliveryDate(deliveryDate);
  }

  Order persistedOrder = orderRepositorySql.save(orderEntity);
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
  Optional<Order> orderOption = orderRepositorySql.findById(orderId);
  if(!orderOption.isPresent()) {
   return null;
  }
  Order persistedOrder = orderOption.get();
  return persistedOrder;
 }

 /**
  * This method definition is for retrieving a list of all the orders.
  *
  * @return List of orders
  */
 @Override
 public final List<Order> getAllOrders() {
  logger.info(OrderConstant.INPROGRES
          + OrderConstant.ORDER_GETALL_INFO);
  return orderRepositorySql.findAll();
 }
}
