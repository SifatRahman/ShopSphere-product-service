package com.example.ShopSphere_product_service.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitMQ.product-exchange.shopsphere}")
    public String productExchangeShopsphere;
    @Value("${rabbitMQ.product-routingKey.shopsphere}")
    public String productRoutingKeyShopsphere;
    @Value("${rabbitMQ.product-queue.shopsphere}")
    public String productQueueNameShopsphere;

    // DLQ exchange and queue
    public static final String PRODUCT_DLQ_EXCHANGE = "product.dlq.exchange";
    public static final String PRODUCT_DLQ_QUEUE = "product.dlq.queue";
    public static final String PRODUCT_DLQ_ROUTING_KEY = "product.dlq.routing.key";


    @Bean
    public Queue shopsphereProductQueue() {
        return QueueBuilder.durable(productQueueNameShopsphere)
                .withArgument("x-dead-letter-exchange", PRODUCT_DLQ_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", PRODUCT_DLQ_ROUTING_KEY)
                .build();
    }
    @Bean
    public DirectExchange shopsphereProductTopicExchange() {
        return new DirectExchange(productExchangeShopsphere);
    }
    @Bean
    public Binding mainBinding(){
        return BindingBuilder.bind(shopsphereProductQueue())
                .to(shopsphereProductTopicExchange())
                .with(productRoutingKeyShopsphere);
    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(PRODUCT_DLQ_EXCHANGE);
    }

    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable(PRODUCT_DLQ_QUEUE).build();
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlqQueue())
                .to(dlqExchange())
                .with(PRODUCT_DLQ_ROUTING_KEY);
    }

}
