package com.hcl.cloud.order.dto;

import com.hcl.cloud.order.entity.Status;

public class CartResponse {

    private ShoppingCart data;

    private Response status;

    public ShoppingCart getData() {
        return data;
    }

    public void setData(ShoppingCart data) {
        this.data = data;
    }

    public Response getStatus() {
        return status;
    }

    public void setStatus(Response status) {
        this.status = status;
    }
}
