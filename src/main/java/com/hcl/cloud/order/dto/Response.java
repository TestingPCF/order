package com.hcl.cloud.order.dto;

/**
 * Response entity.
 * @author shikhar.a
 */
public class Response {

    /**
     * status.
     */
    private int code;

    /**
     * Status Message.
     */
    private String message;

    /**
     * getCode.
     * @return code code
     */
    public final int getCode() {
        return code;
    }

    /**
     * setCode.
     * @param codeParam code
     */
    public final void setCode(final int codeParam) {
        this.code = codeParam;
    }

    /**
     * getMessage.
     * @return message message
     */
    public final String getMessage() {
        return message;
    }

    /**
     * setMessage.
     * @param messageParam messageParam
     */
    public final void setMessage(final String messageParam) {
        this.message = messageParam;
    }
}
