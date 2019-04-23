package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is entity class for Order.
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "TORDER")
@Access(value=AccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "ORDER_SEQ")
    private Long orderId;

    private String orderStatus;

    private Date orderDate;

    private Date deliveryDate;

    @NotNull
    private String userEmail;

    @NotNull
    private String paymentMode;

    private String shippingAddress;

    private BigDecimal orderTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<ShoppingItem> shoppingItems;

    public Order() {
    }

    public Order(String orderStatus, Date orderDate, Date deliveryDate, String userEmail, String paymentMode, String shippingAddress, BigDecimal orderTotal, List<ShoppingItem> shoppingItems) {

        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.userEmail = userEmail;
        this.paymentMode = paymentMode;
        this.shippingAddress = shippingAddress;
        this.orderTotal = orderTotal;
        this.shoppingItems = shoppingItems;
    }

    public Order(Long orderId, String orderStatus, Date orderDate, Date deliveryDate, @NotNull String userEmail, @NotNull String paymentMode, String shippingAddress, BigDecimal orderTotal, List<ShoppingItem> shoppingItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.userEmail = userEmail;
        this.paymentMode = paymentMode;
        this.shippingAddress = shippingAddress;
        this.orderTotal = orderTotal;
        this.shoppingItems = shoppingItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    /**
     * This will return a sample order object.
     * [For testing purpose only]
     * @return Order
     */
    public static Order getSampleOrder(){
        Order order = new Order();
        ShoppingItem item = new ShoppingItem("Iphone_16GB", 1, new BigDecimal(100), new BigDecimal(100), new BigDecimal(100));
        item.setOrder(order);
        ArrayList items = new ArrayList();
        items.add(item);
        order.setOrderDate(new Date());
        order.setOrderStatus("IN_PROGRESS");
        order.setOrderTotal(new BigDecimal("200"));
        order.setUserEmail("shikhar.a@hcl.com");
        order.setShippingAddress("Noida, UP");
        order.setPaymentMode("CASH");
        order.setShoppingItems(new ArrayList<ShoppingItem>(items));

        return order;
    }
}
