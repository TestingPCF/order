package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * This is entity class for Shopping Item.
 *
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "shopping_items")
public class ShoppingItem {

    /**
     * shoppingItemId.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_item_sequence")
    @SequenceGenerator(name = "shopping_item_sequence", sequenceName = "SHOPPING_ITEM_SEQ")
    private Long shoppingItemId;

    /**
     * skuCode.
     */
    private String skuCode;

    /**
     * quantity.
     */
    private Integer quantity;

    /**
     * listPrice.
     */
    private BigDecimal listPrice;

    /**
     * salePrice.
     */
    private BigDecimal salePrice;

    /**
     * totalPrice.
     */
    private BigDecimal totalPrice;

    /**
     * order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    @JsonIgnore
    private Order order;

    /**
     * ShoppingItem.
     */
    public ShoppingItem() {
    }

    /**
     * ShoppingItem.
     *
     * @param skuCode Sku Code
     * @param quantity Quantity
     * @param listPrice List Price
     * @param salePrice Sale Price
     * @param totalPrice Total Price
     */
    public ShoppingItem(final String skuCode, final Integer quantity, final BigDecimal listPrice
            , final BigDecimal salePrice, final BigDecimal totalPrice) {
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
    }

    /**
     * ShoppingItem.
     *
     * @param shoppingItemId Item Id
     * @param skuCode Sku Code
     * @param quantity Quantity
     * @param listPrice List Price
     * @param salePrice Sale Price
     * @param totalPrice Total Price
     * @param order Order
     */
    public ShoppingItem(final Long shoppingItemId, final String skuCode, final Integer quantity
            , final BigDecimal listPrice, final BigDecimal salePrice, final BigDecimal totalPrice
            , final Order order) {
        this.shoppingItemId = shoppingItemId;
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
        this.order = order;
    }

    /**
     * getSkuCode.
     *
     * @return skuCode Code
     */
    public final String getSkuCode() {
        return skuCode;
    }

    /**
     * setSkuCode.
     *
     * @param skuCode Code
     */
    public final void setSkuCode(final String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * getQuantity.
     *
     * @return quantity Quantity
     */
    public final Integer getQuantity() {
        return quantity;
    }

    /**
     * setQuantity.
     *
     * @param quantity Quantity
     */
    public final void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * getListPrice.
     *
     * @return listPrice List Price
     */
    public final BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * setListPrice.
     *
     * @param listPrice List Price
     */
    public final void setListPrice(final BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * getSalePrice.
     *
     * @return salePrice Sale Price
     */
    public final BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * setSalePrice.
     *
     * @param salePrice Sale Price
     */
    public final void setSalePrice(final BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * getTotalPrice.
     *
     * @return totalPrice Total Price
     */
    public final BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * setTotalPrice.
     *
     * @param totalPrice Total Price
     */
    public final void setTotalPrice(final BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * getShoppingItemId.
     *
     * @return shoppingItemId Item Id
     */
    public final Long getShoppingItemId() {
        return shoppingItemId;
    }

    /**
     * setShoppingItemId.
     *
     * @param shoppingItemId Item Id
     */
    public final void setShoppingItemId(final Long shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
    }

    /**
     * getOrder.
     *
     * @return order Order object
     */
    public final Order getOrder() {
        return order;
    }

    /**
     * setOrder.
     *
     * @param order Order object
     */
    public final void setOrder(final Order order) {
        this.order = order;
    }
}
