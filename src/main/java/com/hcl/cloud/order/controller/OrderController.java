package com.hcl.cloud.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.OrderEntity;
import com.hcl.cloud.order.service.OrderService;
import com.hcl.cloud.order.util.OrderUtil;
import com.hcl.cloud.order.util.ResponseUtil;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.CollectionUtils;
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
            OrderEntity orderEntity = orderService.checkout(cart);
            return ResponseUtil.getResponseEntity(HttpStatus.CREATED, OrderConstant.ORDER_CREATED
                    + orderEntity.getOrderId(), null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseUtil.getResponseEntity(HttpStatus.BAD_REQUEST, OrderConstant.ORDER_FAILED
                    , null);
        }

       // return ResponseUtil.getResponseEntity(HttpStatus.BAD_REQUEST, OrderConstant.ORDER_FAILED, null);

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
        OrderEntity orderEntity = orderService.getOrder(orderId);
        Order order = OrderUtil.converJsonStringToObject(orderEntity.getOrderJSON());
        return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_SUCCESS, order);
    }

    /**
     * This is a method wo handle GET request, it will return all the methods associated with the logged-in user.
     *
     * @param authToken a GWT token of a logged in user.
     * @return Response Entity
     */
    @GetMapping
    public ResponseEntity getAllOrders(@RequestHeader(value = "authToken", required = true) String authToken) {
        List<OrderEntity> orderEntityList = orderService.getAllOrders();
        List<Order> orderList = null;

        if(!CollectionUtils.isEmpty(orderEntityList)){
            orderEntityList.forEach(
                    (n)-> orderList.add(OrderUtil.converJsonStringToObject(n.getOrderJSON())));

        }
        return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_SUCCESS, orderList);
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
        OrderEntity orderEntity = orderService.getOrder(order.getOrderId());
        Order orderModified = OrderUtil.converJsonStringToObject(orderEntity.getOrderJSON());

        orderModified.setOrderStatus(status);
        orderEntity.setOrderJSON(OrderUtil.converObjectToJsonString(orderModified));
        orderService.updateOrder(orderEntity);
        return ResponseUtil.getResponseEntity(HttpStatus.OK, OrderConstant.ORDER_UPDATED, null);
    }


}
