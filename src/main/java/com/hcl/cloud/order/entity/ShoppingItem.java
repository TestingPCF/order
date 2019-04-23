package com.hcl.cloud.order.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * This is entity class for Shopping Item.
 * @author shikhar.a || ankit-kumar
 */
@Document
public class ShoppingItem {

    private String skuCode;

    private Integer quantity;

    private BigDecimal listPrice;

    private BigDecimal salePrice;

    private BigDecimal totalPrice;

    public ShoppingItem() {
    }

    public ShoppingItem(String skuCode, Integer quantity, BigDecimal listPrice, BigDecimal salePrice, BigDecimal totalPrice) {
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
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
}
