package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "shopping_item_sequence")
    @SequenceGenerator(name = "shopping_item_sequence",
            sequenceName = "SHOPPING_ITEM_SEQ")
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
     * @param skuCodeObj    Sku Code
     * @param quantityObj   Quantity
     * @param listPriceObj  List Price
     * @param salePriceObj  Sale Price
     * @param totalPriceObj Total Price
     */
    public ShoppingItem(String skuCodeObj,
                        Integer quantityObj,
                        BigDecimal listPriceObj,
                        BigDecimal salePriceObj,
                        BigDecimal totalPriceObj) {
        this.skuCode = skuCodeObj;
        this.quantity = quantityObj;
        this.listPrice = listPriceObj;
        this.salePrice = salePriceObj;
        this.totalPrice = totalPriceObj;
    }

    /**
     * ShoppingItem.
     *
     * @param shoppingItemIdObj Item Id
     * @param skuCodeObj        Sku Code
     * @param quantityObj       Quantity
     * @param listPriceObj      List Price
     * @param salePriceObj      Sale Price
     * @param totalPriceObj     Total Price
     * @param orderObj          Order
     */
    public ShoppingItem(Long shoppingItemIdObj,
                        String skuCodeObj,
                        Integer quantityObj,
                        BigDecimal listPriceObj,
                        BigDecimal salePriceObj,
                        BigDecimal totalPriceObj,
                        Order orderObj) {
        this.shoppingItemId = shoppingItemIdObj;
        this.skuCode = skuCodeObj;
        this.quantity = quantityObj;
        this.listPrice = listPriceObj;
        this.salePrice = salePriceObj;
        this.totalPrice = totalPriceObj;
        this.order = orderObj;
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
     * @param skuCodeObj Code
     */
    public final void setSkuCode(final String skuCodeObj) {
        this.skuCode = skuCodeObj;
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
     * @param quantityObj Quantity
     */
    public final void setQuantity(final Integer quantityObj) {
        this.quantity = quantityObj;
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
     * @param listPriceObj List Price
     */
    public final void setListPrice(final BigDecimal listPriceObj) {
        this.listPrice = listPriceObj;
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
     * @param salePriceObj Sale Price
     */
    public final void setSalePrice(final BigDecimal salePriceObj) {
        this.salePrice = salePriceObj;
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
     * @param totalPriceObj Total Price
     */
    public final void setTotalPrice(final BigDecimal totalPriceObj) {
        this.totalPrice = totalPriceObj;
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
     * @param shoppingItemIdObj Item Id
     */
    public final void setShoppingItemId(final Long shoppingItemIdObj) {
        this.shoppingItemId = shoppingItemIdObj;
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
     * @param orderObj Order object
     */
    public final void setOrder(final Order orderObj) {
        this.order = orderObj;
    }
}
