package com.hcl.cloud.order.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.Response;
import com.hcl.cloud.order.entity.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * This is a utility class which can be used to prepare a Http response entity.
 *
 * @author shikhar.a || ankit-kumar
 */
public final class ResponseUtil {

    /**
     * Private constructor.
     *
     * @param obj Object
     */
    private ResponseUtil(final Object obj) {
        System.out.println(obj);
    }

    /**
     * This method will prepare a ResponseEntity and return the same.
     *
     * @param httpStatus     http Status
     * @param responseString response String
     * @param data           data
     * @return ResponseEntity entity object
     */
    public static ResponseEntity getResponseEntity(
            final HttpStatus httpStatus,
            final String responseString,
            final Object data) {
        Status status = new Status(httpStatus,
                responseString);
        Response<Order> response = null;
        if (data instanceof Collection) {
            List<Order> orderList = (List<Order>) data;
            response = new Response.Builder<Order>(status)
                    .setCollection(orderList).build();
        } else {
            Order order = (Order) data;
            response = new Response.Builder<Order>(status)
                    .setEntity(order)
                    .build();
        }

        return new ResponseEntity<Response<Order>>(response,
                httpStatus);
    }

    public static CartResponse getCartResponse(
            ResponseEntity<Object> cartResponse)
            throws IOException {
        JsonNode jsonNode =
                new ObjectMapper().valueToTree(cartResponse.getBody());
        String json =
                new ObjectMapper().writeValueAsString(jsonNode);
        CartResponse response = new ObjectMapper()
                .readValue(json, CartResponse.class);
        return response;
    }
}
