package com.hcl.cloud.order.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.order.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OrderUtil {

    private static Logger logger = LoggerFactory.getLogger(OrderUtil.class);

    public static String converObjectToJsonString(Object object) {
        if (object != null) {
            try {
                return new ObjectMapper().writeValueAsString(object);
            } catch (JsonProcessingException e) {
                logger.info("Unable to convert object into JSON string");
            }

        }
        return null;

    }

    public static Order converJsonStringToObject(String object) {
        if (object != null) {
            try {
                return new ObjectMapper().readValue(object, Order.class);
            } catch (JsonProcessingException e) {
                logger.info("Unable to convert JSON string into object");
            } catch (IOException e) {
                logger.info("Unable to convert JSON string into object");
            }

        }
        return null;

    }

}
