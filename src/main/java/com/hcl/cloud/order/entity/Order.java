package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Access;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.SequenceGenerator;
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



    public Order(Long orderId, String orderStatus, Date orderDate,
                 Date deliveryDate, @NotNull String userEmail,
                 @NotNull String paymentMode, String shippingAddress,
                 BigDecimal orderTotal, List<ShoppingItem> shoppingItems) {
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
     * Constant PRICE.
     */
    private static final int PRICE = 100;

    /**
     * orderId.
     */
    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence",
            sequenceName = "ORDER_SEQ")
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
     * This will return a sample order object. [For testing purpose only]
     *
     * @return Order Order object
     */
    public static Order getSampleOrder() {
        Order order = new Order();
        ShoppingItem item = new ShoppingItem("Iphone_16GB",
                1,
                new BigDecimal(PRICE), new BigDecimal(PRICE),
                new BigDecimal(PRICE));
        item.setOrder(order);
        ArrayList items = new ArrayList();
        items.add(item);
        order.setOrderDate(new Date());
        order.setOrderStatus("IN_PROGRESS");
        order.setOrderTotal(new BigDecimal(PRICE));
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
     * @param orderIdObj Order Id
     */
    public final void setOrderId(final Long orderIdObj) {
        this.orderId = orderIdObj;
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
     * @param orderStatusObj status
     */
    public final void setOrderStatus(final String orderStatusObj) {
        this.orderStatus = orderStatusObj;
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
     * @param orderDateObj order date
     */
    public final void setOrderDate(final Date orderDateObj) {
        this.orderDate = orderDateObj;
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
     * @param deliveryDateObj date
     */
    public final void setDeliveryDate(final Date deliveryDateObj) {
        this.deliveryDate = deliveryDateObj;
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
     * @param userEmailObj email
     */
    public final void setUserEmail(final String userEmailObj) {
        this.userEmail = userEmailObj;
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
     * @param paymentModeObj payment mode
     */
    public final void setPaymentMode(final String paymentModeObj) {
        this.paymentMode = paymentModeObj;
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
     * @param shippingAddressObj address
     */
    public final void setShippingAddress(final String shippingAddressObj) {
        this.shippingAddress = shippingAddressObj;
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
     * @param orderTotalObj total
     */
    public final void setOrderTotal(final BigDecimal orderTotalObj) {
        this.orderTotal = orderTotalObj;
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
     * @param shoppingItemsObj items
     */
    public final void setShoppingItems(
            final List<ShoppingItem> shoppingItemsObj) {
        this.shoppingItems = shoppingItemsObj;
    }
}
