package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void checkIsExistAtLeastOneFriend() {
        User user = new User();
        User friendUser = new User();
        user.addFriend(friendUser);

        boolean result = user.hasAtLeastOneFriend(friendUser);

        assertTrue(result);
    }

    @Test
    public void checkIsNotExistFriends() {
        User user = new User();
        User friendUser = new User();

        boolean result = user.hasAtLeastOneFriend(friendUser);

        assertFalse(result);
    }

}
