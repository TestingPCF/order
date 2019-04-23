package com.hcl.cloud.order.service;

import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.OrderEntity;
import com.hcl.cloud.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * This is a service interface for order related operation.
 * @author shikhar.a || ankit-kumar
 */
public interface OrderService {

    /**
     * This method declaration is for saving a new order.
     *
     * @param cart object to be saved
     * @return Saved order object
     */
    public OrderEntity checkout(Cart cart);

    /**
     * This method declaration is for updating an order.
     *
     * @param order object to be saved
     * @return Saved order object
     */
    public OrderEntity updateOrder(OrderEntity order);

    /**
     * This method declaration is for retrieving the details of a particular order.
     *
     * @param orderId
     * @return Order object
     */
    public OrderEntity getOrder(Long orderId);

    /**
     * This method declaration is for retrieving a list of all the orders.
     *
     * @return List of orders
     */
    public List<OrderEntity> getAllOrders();
}
