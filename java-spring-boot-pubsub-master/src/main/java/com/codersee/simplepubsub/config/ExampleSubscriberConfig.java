package com.codersee.simplepubsub.config;

import com.codersee.simplepubsub.consumer.ExampleConsumer;
import com.codersee.simplepubsub.consumer.PubSubConsumer;
import com.codersee.simplepubsub.model.MessageEntity;
import com.codersee.simplepubsub.model.SubscriberRegistry;
import com.google.cloud.pubsub.v1.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ExampleSubscriberConfig {

    private static final Logger log = LoggerFactory.getLogger(ExampleSubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;
    private final PubSubConsumer<MessageEntity> exampleConsumer;
    private final SubscriberRegistry sr;

    public ExampleSubscriberConfig(PubSubTemplate pubSubTemplate, ExampleConsumer exampleConsumer,SubscriberRegistry sr) {
        this.pubSubTemplate = pubSubTemplate;
        this.exampleConsumer = exampleConsumer;
        this.sr=sr;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        log.info("Subscribing to {}", exampleConsumer.subscription());

        sr.addSubsciber(pubSubTemplate.subscribeAndConvert(
            exampleConsumer.subscription(),
            exampleConsumer.messageConsumer(),
            exampleConsumer.payloadType()
        ));
    }
}
