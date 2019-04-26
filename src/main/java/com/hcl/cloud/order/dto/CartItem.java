package com.hcl.cloud.order.dto;

import java.math.BigDecimal;

/**
 * This is a dto for cart item.
 * @author shikhar.a
 */
public class CartItem {

    /**
     * itemCode.
     */
    private String itemCode;

    /**
     * productName.
     */
    private String productName;

    /**
     * quantity.
     */
    private int quantity;

    /**
     * salePrice.
     */
    private BigDecimal salePrice;

    /**
     * listPrice.
     */
    private BigDecimal listPrice;

    /**
     * Getter itemCode.
     * @return itemCode code
     */
    public final String getItemCode() {
        return itemCode;
    }

    /**
     * setItemCode.
     * @param itemCodeParam code
     */
    public final void setItemCode(final String itemCodeParam) {
        this.itemCode = itemCodeParam;
    }

    /**
     * getQuantity.
     * @return quantity quantity
     */
    public final int getQuantity() {
        return quantity;
    }

    /**
     * setQuantity.
     * @param quantityParam quantity
     */
    public final void setQuantity(final int quantityParam) {
        this.quantity = quantityParam;
    }

    /**
     * getSalePrice.
     * @return salePrice salePrice
     */
 public final BigDecimal getSalePrice() {
  return salePrice;
 }

    /**
     * setSalePrice.
     * @param salePriceParam salePriceParam
     */
 public final void setSalePrice(final BigDecimal salePriceParam) {
  this.salePrice = salePriceParam;
 }

    /**
     * getListPrice.
     * @return listPrice listPrice
     */
 public final BigDecimal getListPrice() {
  return listPrice;
 }

    /**
     * setListPrice.
     * @param listriceParam listriceParam
     */
 public final void setListPrice(final BigDecimal listriceParam) {
  this.listPrice = listriceParam;
 }

    /**
     * getProductName.
     * @return productName productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setProductName.
     * @param productName productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
