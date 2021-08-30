package com.codersee.simplepubsub.publisher;


import com.codersee.simplepubsub.model.MessageEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExamplePublisher {

    private final String topic;
    private final PubSubTemplate pubSubTemplate;

    public ExamplePublisher(
        @Value("${pubsub-example.topic.name}") String topic,
        PubSubTemplate pubSubTemplate) {
        this.topic = topic;
        this.pubSubTemplate = pubSubTemplate;
    }

    public void publish(MessageEntity payload) {
        pubSubTemplate.publish(topic, payload);
    }
}
