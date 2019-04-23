package com.hcl.cloud.order.controller;

import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.service.OrderService;
import com.hcl.cloud.order.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for Order, it will be responsible to handle all the order related requests.
 * @author shikhar.a || ankit-kumar
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * This is an autowired object of our order service.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * This is a method to handle POST requests for checkout process.
     *
     * @param cart
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity createOrder(@RequestBody Cart cart) {
        try {
            Order orderEntity = orderService.checkout(cart);
            return ResponseUtil.getResponseEntity(HttpStatus.CREATED, OrderConstant.ORDER_CREATED
                    + orderEntity.getOrderId(), null);
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, OrderConstant.ORDER_FAILED
                    , null);
        }
    }

    /**
     * This is a method to handle GET requests for a particular order id.
     *
     * @param orderId   order Id.
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId
            , @RequestHeader(value = "authToken", required = true) String authToken) {
        try{
            Order orderEntity = orderService.getOrder(orderId);
            return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_SUCCESS, orderEntity);
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, OrderConstant.ORDER_FAILED
                    , null);
        }
    }

    /**
     * This is a method wo handle GET request, it will return all the methods associated with the logged-in user.
     *
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     */
    @GetMapping
    public ResponseEntity getAllOrders(@RequestHeader(value = "authToken", required = true) String authToken) {
        try {
            List<Order> orderEntityList = orderService.getAllOrders();
            return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_SUCCESS, orderEntityList);
        }  catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, OrderConstant.ORDER_FAILED
                    , null);
        }
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
        try {
            String status = order.getOrderStatus();
            Order orderEntity = orderService.getOrder(order.getOrderId());
            orderEntity.setOrderStatus(status);
            orderService.updateOrder(orderEntity);
            return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_UPDATED, null);
        }  catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, OrderConstant.ORDER_FAILED
                    , null);
        }
    }


}
