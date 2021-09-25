package org.example.receipt.entity;

import java.util.List;

public class ReceiptDTO {
    private List<ProductDTO> productList;
    private String subtotal;
    private String tax;
    private String total;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item                               price                 qty").append(System.lineSeparator());
        if (productList != null) {
            this.getProductList().forEach(p -> {
                this.appendFixLength(sb, p.getProductName(), 20, false);
                this.appendFixLength(sb, "$" + p.getPrice(), 20, true);
                this.appendFixLength(sb, p.getQuantity()+"", 20, true);
                sb.append(System.lineSeparator());
            });

        }

        this.appendFixLength(sb, "subTotal:", 40, false);
        this.appendFixLength(sb, "$" + this.getSubtotal(), 20, true);
        sb.append(System.lineSeparator());

        this.appendFixLength(sb, "tax:", 40, false);
        this.appendFixLength(sb, "$" + this.getTax(), 20, true);
        sb.append(System.lineSeparator());

        this.appendFixLength(sb, "total:", 40, false);
        this.appendFixLength(sb, "$" + this.getTotal(), 20, true);
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private void appendFixLength(StringBuilder sb, String value, int length, boolean tail) {
        if (value.length() > length) {
            sb.append(value.substring(length));
        } else {
            if (!tail) {
                sb.append(value);
                for (int i = 0; i < length - value.length(); i++) {
                    sb.append(" ");
                }
            } else {
                for (int i = 0; i < 20 - value.length(); i++) {
                    sb.append(" ");
                }
                sb.append(value);
            }
        }
    }
}
