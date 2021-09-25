package org.example.receipt.entity;


import java.util.List;

public class RequestDTO {

    private String location;
    private List<ProductDTO> productList;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "location='" + location + '\'' +
                ", productList=" + productList +
                '}';
    }
}
