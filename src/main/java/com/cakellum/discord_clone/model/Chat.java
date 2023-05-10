package com.cakellum.discord_clone.model;

import java.util.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Getter
    @Setter
    private Long chatId;
    @Getter
    @Setter
    private List<Message> chatLog;
    @Getter
    @Setter
    private User owner;
    @Getter
    @Setter
    private List<User> membersList;
    @Getter
    @Setter
    private String title;

    public void addMessage(Message newChat) {
        chatLog.add(newChat);
    }

    public Boolean addMember(User newUser) {
        if (!membersList.contains(newUser)) {
            membersList.add(newUser);
            return true;
        }
        return false;
    }

    public Boolean kickMember(User badMember) {
        if (!membersList.remove(badMember)) {
            return false;
        }
        return true;
    }

    public Boolean kickMember(long badMemberId) {
        User badMember = membersList.stream().filter(user -> (user.getUserId() == badMemberId)).findAny().orElse(null);
        if (badMember == null || !membersList.remove(badMember)) {
            return false;
        }
        return true;
    }

    public Boolean deleteMessage(Message message) {
        if (!chatLog.remove(message)) {
            return false;
        }
        return true;
    }

    public Boolean isUserMember(long userId) {
        for (User u : membersList) {
            if (u.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }
}
