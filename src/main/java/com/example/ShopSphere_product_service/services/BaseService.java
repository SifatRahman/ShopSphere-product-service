package com.example.ShopSphere_product_service.services;

import com.example.ShopSphere_product_service.utils.exceptions.RequiredResourceNotFoundException;
import com.example.ShopSphere_product_service.utils.exceptions.ResourceAlreadyExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BaseService {
    @PersistenceContext
    protected EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @SneakyThrows
    protected void processException(Exception e) {
        logger.error("Exception occurred : ", e);
        e = parseExceptions(e);
        String message = e.getMessage();
        if (e instanceof RequiredResourceNotFoundException) {
            throw new RequiredResourceNotFoundException(message);
        }
        if (e instanceof ResourceAlreadyExistsException) {
            throw new ResourceAlreadyExistsException(message);
        }
        throw new Exception(message);
    }

    public <T extends Throwable> Exception parseExceptions(T t) {
        if (t == null) {
            return null;
        }

        Throwable cause = t;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        return (Exception) cause;
    }
}
