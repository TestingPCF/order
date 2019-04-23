package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is entity class for Order.
 *
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "TORDER")
@Access(value = AccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    /**
     * orderId.
     */
    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "ORDER_SEQ")
    private Long orderId;

    /**
     * orderStatus.
     */
    private String orderStatus;

    /**
     * orderDate.
     */
    private Date orderDate;

    /**
     * deliveryDate.
     */
    private Date deliveryDate;

    /**
     * userEmail.
     */
    @NotNull
    private String userEmail;

    /**
     * paymentMode.
     */
    @NotNull
    private String paymentMode;

    /**
     * shippingAddress.
     */
    private String shippingAddress;

    /**
     * orderTotal.
     */
    private BigDecimal orderTotal;

    /**
     * shoppingItems.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<ShoppingItem> shoppingItems;

    /**
     * Order.
     */
    public Order() {
    }

    /**
     * Order.
     *
     * @param orderStatus Order Status
     * @param orderDate Order Date
     * @param deliveryDate Delivery Date
     * @param userEmail User Email
     * @param paymentMode Payment Maode
     * @param shippingAddress Shipping Address
     * @param orderTotal Order Total
     * @param shoppingItems Shopping Items
     */
    public Order(final String orderStatus, final Date orderDate, final Date deliveryDate
            , final String userEmail, final String paymentMode, final String shippingAddress
            , final BigDecimal orderTotal, final List<ShoppingItem> shoppingItems) {

        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.userEmail = userEmail;
        this.paymentMode = paymentMode;
        this.shippingAddress = shippingAddress;
        this.orderTotal = orderTotal;
        this.shoppingItems = shoppingItems;
    }

    /**
     * Order.
     *
     * @param orderId Order Id
     * @param orderStatus Order Status
     * @param orderDate Order Date
     * @param deliveryDate Deliver Date
     * @param userEmail User Email
     * @param paymentMode Payment Mode
     * @param shippingAddress Shipping Address
     * @param orderTotal Order Total
     * @param shoppingItems Shopping Items
     */
    public Order(final Long orderId, final String orderStatus, final Date orderDate, final Date deliveryDate
            , final @NotNull String userEmail, final @NotNull String paymentMode, final String shippingAddress
            , final BigDecimal orderTotal, final List<ShoppingItem> shoppingItems) {
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

    /**
     * This will return a sample order object.
     * [For testing purpose only]
     *
     * @return Order Order object
     */
    public static Order getSampleOrder() {
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

    /**
     * getOrderId.
     *
     * @return orderId Order Id
     */
    public final Long getOrderId() {
        return orderId;
    }

    /**
     * setOrderId.
     *
     * @param orderId Order Id
     */
    public final void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    /**
     * getOrderStatus.
     *
     * @return orderStatus status
     */
    public final String getOrderStatus() {
        return orderStatus;
    }

    /**
     * setOrderStatus.
     *
     * @param orderStatus status
     */
    public final void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * getOrderDate.
     *
     * @return orderDate order date
     */
    public final Date getOrderDate() {
        return orderDate;
    }

    /**
     * setOrderDate.
     *
     * @param orderDate order date
     */
    public final void setOrderDate(final Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * getDeliveryDate.
     *
     * @return deliveryDate date
     */
    public final Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * setDeliveryDate.
     *
     * @param deliveryDate date
     */
    public final void setDeliveryDate(final Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * getUserEmail.
     *
     * @return userEmail email
     */
    public final String getUserEmail() {
        return userEmail;
    }

    /**
     * setUserEmail.
     *
     * @param userEmail email
     */
    public final void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * getPaymentMode.
     *
     * @return paymentMode payment mode
     */
    public final String getPaymentMode() {
        return paymentMode;
    }

    /**
     * setPaymentMode.
     *
     * @param paymentMode payment mode
     */
    public final void setPaymentMode(final String paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * getShippingAddress.
     *
     * @return shippingAddress address
     */
    public final String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * setShippingAddress.
     *
     * @param shippingAddress address
     */
    public final void setShippingAddress(final String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * getOrderTotal.
     *
     * @return orderTotal total
     */
    public final BigDecimal getOrderTotal() {
        return orderTotal;
    }

    /**
     * setOrderTotal.
     *
     * @param orderTotal total
     */
    public final void setOrderTotal(final BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * getShoppingItems.
     *
     * @return shoppingItems items
     */
    public final List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    /**
     * setShoppingItems.
     *
     * @param shoppingItems items
     */
    public final void setShoppingItems(final List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }
}
