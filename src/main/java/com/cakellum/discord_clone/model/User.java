package com.cakellum.discord_clone.model;

import java.util.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private List<User> friendList;
    @Getter
    @Setter
    private List<User> blockedList;
    @Getter
    @Setter
    private Community[] communityList;

    public Boolean addFriend(User newFriend) {
        friendList.add(newFriend);
        return true;
    }

    public Boolean blockUser(User newBlockedUser) {
        removeFriend(newBlockedUser);
        blockedList.add(newBlockedUser);
        return true;
    }

    public Boolean removeFriend(User possibleFriend) {
        if (friendList.contains(possibleFriend)) {
            friendList.remove(possibleFriend);
            return true;
        }
        return false;
    }

    public Boolean unblockUser(User possiblyBlockedUser) {
        if (blockedList.contains(possiblyBlockedUser)) {
            blockedList.remove(possiblyBlockedUser);
            return true;
        }
        return false;
    }
}
