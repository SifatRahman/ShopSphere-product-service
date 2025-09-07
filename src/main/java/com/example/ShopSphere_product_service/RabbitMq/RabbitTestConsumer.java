package com.example.ShopSphere_product_service.RabbitMq;

import com.example.ShopSphere_product_service.dtos.ProductResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitTestConsumer {


//    @RabbitListener(queues = "${rabbitMQ.product-queue.shopsphere}")
    @SneakyThrows
    public <T> void receiveMessage(String message) {
        ProductResponseDTO productResponseDTO = new ObjectMapper().
                registerModule(new JavaTimeModule()).readValue(message,ProductResponseDTO.class);
        System.out.println("📩 Received from MQ: " + productResponseDTO);
    }
}
