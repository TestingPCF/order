package com.hcl.cloud.order.dto;

/**
 * CartResponse entity.
 * @author shikhar.a
 */
public class CartResponse {
    /**
     * ShoppingCart.
     */
    private ShoppingCart data;
    /**
     * Response.
     */
    private Response status;

    /**
     * getData.
     * @return data ShoppingCart
     */
    public ShoppingCart getData() {
        return data;
    }

    /**
     * setData.
     * @param dataParam ShoppingCart
     */
    public final void setData(final ShoppingCart dataParam) {
        this.data = dataParam;
    }

    /**
     * getStatus.
     * @return status status
     */
    public final Response getStatus() {
        return status;
    }

    /**
     * setStatus.
     * @param statusParam statusParam
     */
    public final void setStatus(final Response statusParam) {
        this.status = statusParam;
    }
}
