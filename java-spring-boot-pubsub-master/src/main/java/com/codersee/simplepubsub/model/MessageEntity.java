package com.codersee.simplepubsub.model;

import java.time.LocalDateTime;

public class MessageEntity {

    private final LocalDateTime timestamp;
    private final String message;

    public MessageEntity(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
