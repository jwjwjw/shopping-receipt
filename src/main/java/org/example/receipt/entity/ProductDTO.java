package org.example.receipt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.receipt.util.StringUtil;

import java.math.BigDecimal;

public class ProductDTO {

    private String productName;
    private BigDecimal price;
    private int quantity;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonIgnore
    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal(quantity));
    }

    @JsonIgnore
    public BigDecimal getTax(BigDecimal rate) {
        return this.getTotalPrice().multiply(rate);
    }

    @JsonIgnore
    public boolean isValid() {
        return !StringUtil.isNullOrEmpty(productName) && price.compareTo(BigDecimal.valueOf(0)) >= 0 && quantity >= 0;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
