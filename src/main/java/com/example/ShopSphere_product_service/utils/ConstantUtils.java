package com.example.ShopSphere_product_service.utils;

import java.util.UUID;

public class ConstantUtils {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}