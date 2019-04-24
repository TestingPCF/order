package com.hcl.cloud.order.entity;

/**
 * Inventory DTO.
 * @author shikhar.a || ankit-kumar
 */
public class Inventory {

    /**
     * skuCode.
     */
    private String skuCode;

    /**
     * quantity.
     */
    private long quantity;

    /**
     * Inventory constructor.
     * @param skuCode Sku Code
     * @param quantity Quantity
     */
    public Inventory(String skuCode, long quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    /**
     * getSkuCode.
     * @return skuCode
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * setSkuCode.
     * @param skuCode setSkuCode
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * getQuantity.
     * @return getQuantity getQuantity
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * setQuantity.
     * @param quantity setQuantity
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
