package com.codersee.simplepubsub.consumer;

import org.springframework.cloud.gcp.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;

import java.util.function.Consumer;

public interface PubSubConsumer<T> {

    String subscription();

    Class<T> payloadType();

    Consumer<ConvertedBasicAcknowledgeablePubsubMessage<T>> messageConsumer();
}
