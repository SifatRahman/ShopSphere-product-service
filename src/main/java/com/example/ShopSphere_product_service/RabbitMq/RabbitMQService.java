package com.example.ShopSphere_product_service.RabbitMq;

import com.example.ShopSphere_product_service.dtos.rabbit.EventCommunicationDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQService {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitMQ.product-routingKey.shopsphere}")
    public String routingKeyShopsphere;
    @Value("${rabbitMQ.product-exchange.shopsphere}")
    public String exchangeShopsphere;


    public void sendMessageToMQ(String eventName, Object payload) {

        EventCommunicationDTO eventCommunicationDTO = EventCommunicationDTO.builder()
                .eventName(eventName)
                .payload(payload)
                .build();

        JSONObject payloadObj = new JSONObject(eventCommunicationDTO);
        String message = payloadObj.toString();
        rabbitTemplate.convertAndSend(
                exchangeShopsphere, routingKeyShopsphere, message
        );
        System.out.println("Sent to MQ: " + message);
    }

}
