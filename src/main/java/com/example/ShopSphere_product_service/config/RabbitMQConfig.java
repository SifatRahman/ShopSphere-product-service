package com.example.ShopSphere_product_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitMQ.queue.shopsphere}")
    public String queueNameShopsphere;
    @Value("${rabbitMQ.routingKey.shopsphere}")
    public String routingKeyShopsphere;
    @Value("${rabbitMQ.exchange.shopsphere}")
    public String exchangeShopsphere;

    @Bean
    public Queue shopsphereQueue() {
        return new Queue(queueNameShopsphere);
    }
    @Bean
    public TopicExchange shopsphereTopicExchange() {
        return new TopicExchange(exchangeShopsphere);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(shopsphereQueue()).to(shopsphereTopicExchange()).with(routingKeyShopsphere);
    }

    @Bean
    public String getQueueName() {
        return queueNameShopsphere;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
