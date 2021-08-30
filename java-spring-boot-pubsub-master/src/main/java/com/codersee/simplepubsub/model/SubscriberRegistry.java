package com.codersee.simplepubsub.model;

import com.google.cloud.pubsub.v1.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class SubscriberRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberRegistry.class);
    private List<Subscriber> subscriberList = new ArrayList<Subscriber>();
    public void addSubsciber(Subscriber subscriber){
        this.subscriberList.add(subscriber);
    }
    public void addSubscibers(List<Subscriber> subscribers){
        this.subscriberList.addAll(subscribers);
    }

    public List<Subscriber> getSubscibers(){
        return this.subscriberList;
    }

    public void pausePubsubListeners() {
        Collection<Subscriber> pubsubmessageListenerContainers = this.getSubscibers();
        if (null != pubsubmessageListenerContainers) {
            pubsubmessageListenerContainers.stream().forEach(messageListenerContainer -> {
                if (messageListenerContainer.isRunning()) {
                    LOGGER.info("Stopping pubsub consumer {}", messageListenerContainer);
                    messageListenerContainer.stopAsync();
                    LOGGER.info("Stopped pubsub consumer {}", messageListenerContainer);
                }
            });
        }
    }


    /**
     * This method verifies if all the three flags are not set, will resume the pubsub consumers that are paused earlier.
     * and starts the consumers if they haven't been started so far
     */
    public void resumePubsubListeners() {
        Collection<Subscriber> pubsubmessageListenerContainers = this.getSubscibers();
        if (null != pubsubmessageListenerContainers) {
            pubsubmessageListenerContainers.stream().forEach(messageListenerContainer -> {
                if (!messageListenerContainer.isRunning()) {
                    LOGGER.info("Starting pubsub consumer {}", messageListenerContainer);
                    messageListenerContainer.startAsync();
                    LOGGER.info("Started pubsub consumer {}", messageListenerContainer);
                    
                }
            });
        }
    }
}
