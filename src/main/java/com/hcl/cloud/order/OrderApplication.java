package com.hcl.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a class which will bootstrap our Order application.
 *
 * @author shikhar.a || ankit-kumar
 */
@SpringBootApplication
public final class OrderApplication {

    /**
     * Private Constructor.
     *
     * @param obj Object
     */
    private OrderApplication(final Object obj) {

    }

    /**
     * main.
     *
     * @param args String input array
     */
    public static void main(final String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
