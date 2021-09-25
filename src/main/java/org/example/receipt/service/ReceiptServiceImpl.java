package org.example.receipt.service;


import org.example.receipt.entity.ProductDTO;
import org.example.receipt.entity.ReceiptDTO;
import org.example.receipt.entity.RequestDTO;
import org.example.receipt.util.PreCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptServiceImpl implements ReceiptService, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(ReceiptServiceImpl.class);

    @Value("${tax.CA}")
    private String taxCA;

    @Value("${tax.NY}")
    private String taxNY;

    @Value("${food}")
    private String foodList;

    @Value("${cloth}")
    private String clothList;

    @Value("${stationery}")
    private String stationeryList;

    private Map<String, List<String>> category = new ConcurrentHashMap<>();


    @Override
    public void afterPropertiesSet() {

        System.out.println("afterPropertiesSet");
        log.info(foodList);
        log.info(clothList);
        log.info(stationeryList);

        category.put("food", Arrays.asList(foodList.split(",")));
        category.put("cloth", Arrays.asList(clothList.split(",")));
        category.put("stationery", Arrays.asList(stationeryList.split(",")));

    }

    public void setCategory(Map<String, List<String>> category) {
        this.category = category;
    }

    public void setTaxCA(String taxCA) {
        this.taxCA = taxCA;
    }

    public void setTaxNY(String taxNY) {
        this.taxNY = taxNY;
    }

    @Override
    public ReceiptDTO getReceipt(RequestDTO requestDTO) {

        PreCondition.requestCheck(requestDTO);

        ReceiptDTO receiptDTO = this.calSubTotal(requestDTO);
        receiptDTO.setProductList(requestDTO.getProductList());
        return receiptDTO;

    }

    private ReceiptDTO calSubTotal(RequestDTO requestDTO) {
        ReceiptDTO receipt = new ReceiptDTO();
        String location = requestDTO.getLocation();

        BigDecimal totalTax = new BigDecimal(0);
        BigDecimal subTotalPrice = new BigDecimal(0);
        for (ProductDTO p : requestDTO.getProductList()) {
            if (location.equalsIgnoreCase("CA")) {
                if (this.belowsToCategory("food", p.getProductName())) {
                    log.info(p.getProductName() + "is food" );
                    // tax = 0
                } else {
                    totalTax = totalTax.add(p.getTax(new BigDecimal(taxCA)));
                }

            } else if (location.equalsIgnoreCase("NY")) {
                if (this.belowsToCategory("food", p.getProductName()) || this.belowsToCategory("cloth", p.getProductName())) {
                    // tax = 0
                } else {
                    totalTax = totalTax.add(p.getTax(new BigDecimal(taxNY)));
                }
            }
            subTotalPrice = subTotalPrice.add(p.getTotalPrice());
        }
        totalTax = adjustTax(totalTax);
        subTotalPrice = subTotalPrice.setScale(2, RoundingMode.DOWN);
        BigDecimal totalPrice = subTotalPrice.add(totalTax).setScale(2);
        receipt.setSubtotal(subTotalPrice.toString());
        receipt.setTax(totalTax.toString());
        receipt.setTotal(totalPrice.toString());
        return receipt;
    }

    private BigDecimal adjustTax(BigDecimal tax) {
        return tax.multiply(new BigDecimal(20)).setScale(0, RoundingMode.CEILING)
                .multiply(new BigDecimal(0.05)).setScale(2, RoundingMode.DOWN);
    }


    private boolean belowsToCategory(String category, String productName) {
        return this.category.get(category).contains(productName);
    }

}
