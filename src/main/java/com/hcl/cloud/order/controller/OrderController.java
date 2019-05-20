/*
 * Copyright (c) 2019.
 */
package com.hcl.cloud.order.controller;

import com.hcl.cloud.order.config.ConstantConfig;
import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.exception.BadRequestException;
import com.hcl.cloud.order.service.OrderService;
import com.hcl.cloud.order.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for Order, it will be responsible to handle all the
 * order related requests.
 *
 * @author shikhar.a || ankit-kumar
 */
@RestController
public class OrderController {

    /**
     * Logger.
     */
    private static Logger logger = LoggerFactory
            .getLogger(OrderController.class);

    /**
     * This is an autowired object of our order service.
     */
    @Autowired
    private OrderService orderService;

    /**
     * This is an autowired object of our order service.
     */
    @Autowired
    private ConstantConfig constantConfig;

    /**
     * This is a method to handle POST requests for checkout process.
     * @param authToken Authentication token
     * @return ResponseEntity
     */
    @PostMapping
    public final ResponseEntity createOrder(
            final @RequestHeader(value = OrderConstant.AUTHORIZATION_TOKEN,
                    required = true) String authToken) {
        Order order = new Order();
        try {
            return orderService.checkout(order, authToken);
        } catch (Exception e) {
            logger.info(OrderConstant.ERROR
                    + OrderConstant.ORDER_CREATING_INFO
                    + order.getUserEmail());
            logger.error(e.getMessage(), e);
            return ResponseUtil.getResponseEntity(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    OrderConstant.ORDER_FAILED, null);
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
    public final ResponseEntity<Order> getOrder(
            final @PathVariable Long orderId,
            final @RequestHeader(value = OrderConstant.AUTHORIZATION_TOKEN,
                    required = true)
                    String authToken) {
        try {
            logger.info(OrderConstant.START
                    + OrderConstant.ORDER_FETCHING_INFO
                    + orderId);
            Order orderEntity = orderService.getOrder(orderId);
            logger.info(OrderConstant.COMPLETED
                    + OrderConstant.ORDER_FETCHING_INFO
                    + orderId);
            return ResponseUtil.getResponseEntity(HttpStatus.OK,
                    OrderConstant.ORDER_SUCCESS, orderEntity);
        } catch (Exception e) {
            logger.info(OrderConstant.ERROR
                    + OrderConstant.ORDER_FETCHING_INFO
                    + orderId);
            logger.error(e.getMessage(), e);
            return ResponseUtil.getResponseEntity(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    OrderConstant.ORDER_FAILED, null);
        }
    }

    /**
     * This is a method wo handle GET request, it will return all the methods
     * associated with the logged-in user.
     *
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     */
    @GetMapping
    public final ResponseEntity getAllOrders(
            final @RequestHeader(value = OrderConstant.AUTHORIZATION_TOKEN,
                    required = true) String authToken) {
        try {
            logger.info(OrderConstant.START
                    + OrderConstant.ORDER_GETALL_INFO);
            List<Order> orderEntityList = orderService.getAllOrders();
            logger.info(OrderConstant.COMPLETED
                    + OrderConstant.ORDER_GETALL_INFO);
            return ResponseUtil.getResponseEntity(HttpStatus.OK,
                    constantConfig.getSuccessRetrieve(), orderEntityList);
        } catch (Exception e) {
            logger.info(OrderConstant.ERROR
                    + OrderConstant.ORDER_GETALL_INFO);
            logger.error(e.getMessage(), e);
            return ResponseUtil.getResponseEntity(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    OrderConstant.ORDER_FAILED, null);
        }
    }

    /**
     * This is a method to handle PUT requests for order. We will allow only
     * status and delivery date to get updated.
     *
     * @param order Order object.
     * @return ResponseEntity
     */
    @PutMapping
    public final ResponseEntity updateOrder(final @RequestBody Order order) {
        try {
            logger.info(OrderConstant.START
                    + OrderConstant.ORDER_UPDATING_INFO
                    + order.getOrderId());
            orderService.updateOrder(order);
            logger.info(OrderConstant.COMPLETED
                    + OrderConstant.ORDER_UPDATING_INFO
                    + order.getOrderId());
            return ResponseUtil.getResponseEntity(HttpStatus.OK,
                    OrderConstant.ORDER_UPDATED, null);
        } catch (BadRequestException e) {
            logger.info(OrderConstant.ERROR
                    + OrderConstant.ORDER_UPDATING_INFO
                    + order.getOrderId());
            logger.error(e.getMessage(), e);
            return ResponseUtil.getResponseEntity(
                    HttpStatus.BAD_REQUEST,
                    OrderConstant.ORDER_FAILED + " :: " +
                            e.getMessage(), null);
        } catch (Exception e) {
            logger.info(OrderConstant.ERROR
                    + OrderConstant.ORDER_UPDATING_INFO
                    + order.getOrderId());
            logger.error(e.getMessage(), e);
            return ResponseUtil.getResponseEntity(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    OrderConstant.ORDER_FAILED, null);
        }
    }

}
