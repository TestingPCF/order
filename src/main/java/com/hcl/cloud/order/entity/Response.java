package com.hcl.cloud.order.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

/**
 * This is an entity class, which will represent the complete http response along with data
 * in a json format.
 *
 * @author shikhar.a || ankit-kumar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Response<E> {

    /**
     * status containing message and Httpstatus code.
     */
    private final Status status;

    /**
     * Entity containing data.
     */
    private final E data;

    /**
     * Collection having resource data.
     */
    private final Collection<E> dataSet;

    /**
     * setting builder values.
     *
     * @param builder
     */
    private Response(final Builder<E> builder) {
        this.data = builder.data;
        this.status = builder.status;
        this.dataSet = builder.dataSet;
    }

    /**
     * @return status.
     */
    public final Status getStatus() {
        return status;
    }

    /**
     * Gets the data.
     *
     * @return data.
     */
    public final E getData() {
        return data;
    }

    /**
     * @return Collection
     */
    public final Collection<E> getDataSet() {
        return dataSet;
    }

    /**
     * @param <E>
     * @author singh.ro Builder to set corresponding values.
     */
    public static class Builder<E> {
        /**
         * data.
         */
        private E data;

        /**
         * status.
         */
        private Status status;

        /**
         * dataSet.
         */
        private Collection<E> dataSet;

        /**
         * Object instantiation with mandatory fields.
         *
         * @param status
         */
        public Builder(final Status status) {
            this.status = status;
        }

        /**
         * Builder<E>.
         *
         * @param data Data
         * @return builder Builder.
         */
        public final Builder<E> setEntity(final E data) {
            this.data = data;
            return this;
        }

        /**
         * Builder<E>.
         *
         * @param dataSet dataset
         * @return builder Builder.
         */
        public final Builder<E> setCollection(final Collection<E> dataSet) {
            this.dataSet = dataSet;
            return this;
        }

        /**
         * /** ResponseStatus<E>.
         *
         * @return responseStatus status.
         */
        public final Response<E> build() {
            return new Response<E>(this);
        }
    }

}
