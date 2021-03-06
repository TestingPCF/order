package com.hcl.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * This is a class which will bootstrap our Order application.
 * 
 * @author shikhar.a || ankit-kumar
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {

    /**
     * main.
     * 
     * @param args
     *            String input array
     */
    public static void main(final String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * Print Method.
     */
    public final void printApplication() {
        System.out.println("Application started::");
    }
}
