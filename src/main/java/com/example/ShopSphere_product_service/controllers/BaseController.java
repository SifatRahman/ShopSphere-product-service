package com.example.ShopSphere_product_service.controllers;

import com.example.ShopSphere_product_service.dtos.ResponseModelDTO;
import com.example.ShopSphere_product_service.utils.enums.Status;
import com.example.ShopSphere_product_service.utils.exceptions.RequiredResourceNotFoundException;
import com.example.ShopSphere_product_service.utils.exceptions.ResourceAlreadyExistsException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    @SneakyThrows
    protected ResponseEntity<ResponseModelDTO> exceptionTask(Exception e) {
        e = parseExceptions(e);
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        String message;
        responseModelDTO.setStatus(Status.error.name());
        responseModelDTO.setCorrelation_id(null);
        message = e.getMessage();
        responseModelDTO.setMessage(message);
        if (e instanceof ResourceAlreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(responseModelDTO);
        }
        if (e instanceof RequiredResourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseModelDTO);
        }
        return ResponseEntity.badRequest()
                .body(responseModelDTO);
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
