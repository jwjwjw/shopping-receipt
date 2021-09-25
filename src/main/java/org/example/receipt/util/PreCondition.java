package org.example.receipt.util;

import org.example.receipt.entity.RequestDTO;

public class PreCondition {

    public static void requestCheck(RequestDTO requestDTO) {
        valid(requestDTO != null, "Input should not be null");
        valid(!StringUtil.isNullOrEmpty(requestDTO.getLocation()), "location should not be null");
        valid(!requestDTO.getLocation().equalsIgnoreCase("CA") || !requestDTO.getLocation().equalsIgnoreCase("NY"),
                "Only support location CA and NY now");
        valid(requestDTO.getProductList() != null, "product info should not be null");
        requestDTO.getProductList().forEach(p -> valid(p.isValid(), "product info invalid"));
    }
    public static void valid(Boolean expression, String msg) {
        if (!expression) {
            throw new IllegalArgumentException(msg);
        }
    }


}
