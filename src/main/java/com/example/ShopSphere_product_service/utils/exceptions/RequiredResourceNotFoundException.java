package com.example.ShopSphere_product_service.utils.exceptions;

public class RequiredResourceNotFoundException extends RuntimeException {

    public RequiredResourceNotFoundException(String message) {
        super(message);
    }
}
