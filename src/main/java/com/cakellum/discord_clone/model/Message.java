package com.cakellum.discord_clone.model;

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
}
