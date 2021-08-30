package com.codersee.simplepubsub.config;

import java.beans.BeanProperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gcp.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import com.codersee.simplepubsub.consumer.ExampleConsumer1;
import com.codersee.simplepubsub.consumer.ExampleConsumer;
@Configuration
public class PubSubConfig {
    
    


    
    @Bean
    public JacksonPubSubMessageConverter jacksonPubSubMessageConverter(ObjectMapper objectMapper) {
        return new JacksonPubSubMessageConverter(objectMapper);
    }
}
