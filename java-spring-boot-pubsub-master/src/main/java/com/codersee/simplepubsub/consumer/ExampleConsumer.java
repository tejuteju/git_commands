package com.codersee.simplepubsub.consumer;

import com.codersee.simplepubsub.model.MessageEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
//import static com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.SeekRequest;


@Component
public class ExampleConsumer implements PubSubConsumer<MessageEntity> {

    private static final Logger log = LoggerFactory.getLogger(ExampleConsumer.class);
    private final String subscriptionName;
    //private final SubscriptionAdminClient subscriptionAdminClient;
    public ExampleConsumer(
        @Value("${pubsub-example.subscription.name}") String subscriptionName
    ) {
        this.subscriptionName = subscriptionName;
    }

    @Override
    public String subscription() {
        return subscriptionName;
    }
    
    @Override
    public Class<MessageEntity> payloadType() {
        return MessageEntity.class;
    }

    @Override
    public Consumer<ConvertedBasicAcknowledgeablePubsubMessage<MessageEntity>> messageConsumer() {
        return this::consume;
    }

    private void consume(ConvertedBasicAcknowledgeablePubsubMessage<MessageEntity> message) {
        MessageEntity received = message.getPayload();

        log.info(
            "Received entity: [timestamp= {}, message= {}]",
            received.getTimestamp(),
            received.getMessage()
        );

        message.ack();
    }
    /*public void seek(){
        
    }*/
}