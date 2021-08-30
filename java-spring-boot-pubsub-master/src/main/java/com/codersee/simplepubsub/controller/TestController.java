package com.codersee.simplepubsub.controller;

import com.codersee.simplepubsub.consumer.ExampleConsumer;
//import com.codersee.simplepubsub.consumer.ExampleConsumer1;
import com.codersee.simplepubsub.model.MessageEntity;
import com.codersee.simplepubsub.model.SubscriberRegistry;
import com.codersee.simplepubsub.publisher.ExamplePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.SeekRequest;
import com.google.pubsub.v1.SeekResponse;
import com.google.pubsub.v1.ProjectSubscriptionName;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant; 
import com.google.protobuf.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TestController {

    private final ExamplePublisher publisher;
    private final ExampleConsumer consumer;
    //public final ExampleConsumer1 consumer1;
    @Autowired
    SubscriberRegistry sr;
    public TestController(ExamplePublisher publisher,ExampleConsumer consumer) {
        this.publisher = publisher;
        this.consumer=consumer;
       // this.consumer1=consumer1;
        
        //sr.addSubsciber(consumer);
        //sr.addSubsciber(consumer1.subscription());
    }

    @PostMapping("/topic")
    public void publish(@RequestBody String message) {
        MessageEntity entity = new MessageEntity(LocalDateTime.now(), message);
        publisher.publish(entity);
    }
    @GetMapping("/stopconsumer")
    public void stop(){
       // System.out.print(sr.getSubscibers());
        sr.pausePubsubListeners();
    }
    @GetMapping("/resume")
    public void resume(){

        sr.resumePubsubListeners();
    }
    @PostMapping("/seek")
    public void seek(){
        Instant inst = Instant.parse("2021-08-12T18:39:30.00Z");
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond())
        .setNanos(Instant.now().getNano()).build();
        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
            ProjectSubscriptionName subscription = ProjectSubscriptionName.of("dataflow-320606", "sample-sub");
            SeekRequest request = SeekRequest.newBuilder()
            .setSubscription(subscription.toString()).setTime(timestamp)
            .build();
            SeekResponse response = subscriptionAdminClient.seek(request);
        }catch(Exception e){
            System.out.print(e);
        }
    }
}
