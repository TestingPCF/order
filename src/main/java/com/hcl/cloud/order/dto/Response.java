package com.hcl.cloud.order.dto;

import org.springframework.http.HttpStatus;

public class Response {

    /**
     * status.
     */
    private int code;

    /**
     * Status Message.
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
