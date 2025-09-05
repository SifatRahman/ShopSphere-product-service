package com.example.ShopSphere_product_service.RabbitMq;

import com.example.ShopSphere_product_service.dtos.CreateProductDTO;
import com.example.ShopSphere_product_service.dtos.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RabbitTestProducer {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitMQ.routingKey.shopsphere}")
    public String routingKeyShopsphere;
    @Value("${rabbitMQ.exchange.shopsphere}")
    public String exchangeShopsphere;

    @SneakyThrows
    public <T> void sendMessage(T payload) {

        JSONObject payloadObj = new JSONObject(payload);
        String message = payloadObj.toString();

        rabbitTemplate.convertAndSend(exchangeShopsphere, routingKeyShopsphere, message);
        System.out.println("Sent to MQ: " + message);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(
                exchangeShopsphere, routingKeyShopsphere, message
        );
    }

    public Object sendTestMessageToMQ(){
        ProductResponseDTO message = ProductResponseDTO.builder()
                .id("product.getId()")
                .name("product.getName()")
                .description("product.getDescription()")
                .price(BigDecimal.TEN)
                .image_url("product.getImageUrl()")
                .discount_price(BigDecimal.ONE)
                .stock_quantity(3)
                .category_id("product.getCategoryId()")
                .brand_id("product.getBrandId()")
                .status("product.getStatus()")
                .created_at(LocalDateTime.MIN)
                .updated_at(LocalDateTime.MAX)
                .build();

        this.sendMessage(message);
        return message;
    }
}
