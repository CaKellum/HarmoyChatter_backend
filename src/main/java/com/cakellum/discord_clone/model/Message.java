package com.cakellum.discord_clone.model;

import org.springframework.web.servlet.FlashMapManager;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Getter
    @Setter
    private String messageId;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private Chat owner;
    @Getter
    @Setter
    private User sender;

    public Boolean editMessage(String newMessage) {
        if (!content.equals(newMessage)) {
            setContent(newMessage);
            return false;
        }
        return false;
    }
}
