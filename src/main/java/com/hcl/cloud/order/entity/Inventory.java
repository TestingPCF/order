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
     * @param skuCodeParam Sku Code
     * @param quantityParam Quantity
     */
    public Inventory(String skuCodeParam, long quantityParam) {
        this.skuCode = skuCodeParam;
        this.quantity = quantityParam;
    }

    /**
     * getSkuCode.
     * @return skuCode
     */
    public final String getSkuCode() {
        return skuCode;
    }

    /**
     * setSkuCode.
     * @param skuCodeParam setSkuCode
     */
    public final void setSkuCode(final String skuCodeParam) {
        this.skuCode = skuCodeParam;
    }

    /**
     * getQuantity.
     * @return getQuantity getQuantity
     */
    public final long getQuantity() {
        return quantity;
    }

    /**
     * setQuantity.
     * @param quantityParam setQuantity
     */
    public final void setQuantity(final long quantityParam) {
        this.quantity = quantityParam;
    }
}
