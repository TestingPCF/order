package com.hcl.cloud.order.util;

import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.Response;
import com.hcl.cloud.order.entity.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

/**
 * This is a utility class which can be used to prepare a Http response entity.
 *
 * @author shikhar.a || ankit-kumar
 */
public class ResponseUtil {

    /**
     * This method will prepare a ResponseEntity and return the same.
     *
     * @param httpStatus
     * @param responseString
     * @param data
     * @return
     */
    public static ResponseEntity getResponseEntity(HttpStatus httpStatus, String responseString
            , Object data) {
        ResponseEntity entity;
        Status status = new Status(httpStatus, responseString);
        Response<Order> response = null;//new Response.Builder<String>(status).build();
        if (data instanceof Collection) {
            List<Order> orderList = (List<Order>) data;
            response = new Response.Builder<Order>(status).setCollection(orderList).build();
        } else {
            Order order = (Order) data;
            response = new Response.Builder<Order>(status).setEntity(order).build();
        }

        return new ResponseEntity<Response<Order>>(response, httpStatus);
    }
}
