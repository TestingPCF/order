package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * This is entity class for Shopping Item.
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "shopping_items")
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "shopping_item_sequence")
    @SequenceGenerator(name = "shopping_item_sequence", sequenceName = "SHOPPING_ITEM_SEQ")
    private Long shoppingItemId;

    private String skuCode;

    private Integer quantity;

    private BigDecimal listPrice;

    private BigDecimal salePrice;

    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    @JsonIgnore
    private Order order;

    public ShoppingItem() {
    }

    public ShoppingItem(String skuCode, Integer quantity, BigDecimal listPrice, BigDecimal salePrice, BigDecimal totalPrice) {
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
    }

    public ShoppingItem(Long shoppingItemId, String skuCode, Integer quantity, BigDecimal listPrice, BigDecimal salePrice, BigDecimal totalPrice, Order order) {
        this.shoppingItemId = shoppingItemId;
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
        this.order = order;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getShoppingItemId() {
        return shoppingItemId;
    }

    public void setShoppingItemId(Long shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
