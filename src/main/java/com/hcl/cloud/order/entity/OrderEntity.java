package com.hcl.cloud.order.entity;

import javax.persistence.*;

/**
 * This is entity class for Order.
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "TORDER")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "ORDER_SEQ")
    private Long orderId;

    @Column(name = "ORDER_JSON")
    private String orderJSON;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderJSON() {
        return orderJSON;
    }

    public void setOrderJSON(String orderJSON) {
        this.orderJSON = orderJSON;
    }
}
