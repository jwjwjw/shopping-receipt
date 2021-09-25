package org.example.receipt.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null ? true : false : str2 == null ? false : str1.equalsIgnoreCase(str2);
    }
}
