package com.hcl.cloud.order.controller;

import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is controller class for Order, it will be responsible to handle all the order related requests.
 * @author shikhar.a || ankit-kumar
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * This is an autowired object of our mongo repository.
     */
    @Autowired
    private OrderRepository orderRepository;

    /**
     * This is a method to handle POST requests for checkout process.
     *
     * @param cart
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity createOrder(@RequestBody Cart cart) {
        Order order = Order.getSampleOrder();
        orderRepository.save(order);
        return new ResponseEntity<>("Your order Id is :: "+ order.getOrderId(), HttpStatus.CREATED);
    }

    /**
     * This is a method to handle GET requests for a particular order id.
     *
     * @param orderId   order Id.
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId
            , @RequestHeader(value = "authToken", required = true) String authToken) {
        Order order = orderRepository.findById(orderId).get();
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    /**
     * This is a method wo handle GET request, it will return all the methods associated with the logged-in user.
     *
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     */
    @GetMapping
    public ResponseEntity getAllOrders(@RequestHeader(value = "authToken", required = true) String authToken) {
        List<Order> orderList = orderRepository.findAll();
        return new ResponseEntity<List>(orderList, HttpStatus.OK);
    }

    /**
     * This is a method to handle PUT requests for order.
     * We will allow only status and delivery date to get updated.
     *
     * @param order
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity updateOrder(@RequestBody Order order) {
        String status = order.getOrderStatus();
        order = orderRepository.findById(order.getOrderId()).get();
        order.setOrderStatus(status);
        orderRepository.save(order);
        return new ResponseEntity<>(order.getOrderId() + " Updated Successfully", HttpStatus.OK);
    }
}
