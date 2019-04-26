package com.hcl.cloud.order.service;

import com.hcl.cloud.order.entity.Order;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * This is a service interface for order related operation.
 *
 * @author shikhar.a || ankit-kumar
 */
public interface OrderService {
    /**
     * checkout.
     * @param order Object
     * @param authToken auth token
     * @return ResponseEntity entity
     * @throws IOException IO Exception
     */
    ResponseEntity<Object> checkout(Order order, String authToken)
            throws IOException;

  /**
   * This method declaration is for updating an order.
   *
   * @param order
   *            object to be saved
   * @return Saved order object
   */
  Order updateOrder(Order order);

  /**
   * This method declaration is for retrieving the details of a particular
   * order.
   *
   * @param orderId
   *            ID
   * @return Order object
   */
  Order getOrder(Long orderId);

  /**
   * This method declaration is for retrieving a list of all the orders.
   *
   * @return List of orders
   */
  List<Order> getAllOrders();
}
