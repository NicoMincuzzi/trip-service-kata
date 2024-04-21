package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.util.Assert.isTrue;

public class TripServiceTest extends TripService {

    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final User GUEST_USER = null;
    private static final Trip TRIP = new Trip();
    private User loggedInUser;

    @Test
    public void whenTheUserIsLoggedInThenShowTheContent() {
        User user = new User();
        user.addTrip(TRIP);
        user.addTrip(TRIP);
        user.addFriend(REGISTERED_USER);
        loggedInUser = REGISTERED_USER;

        List<Trip> result = new TestableTripService().getTripsByUser(user);

        isTrue(result.size() == 2);
    }

    @Test
    public void whenTheUserIsLoggedInAndHasFriendThenCanSeeHisTrip() {
        User user = new User();
        user.addFriend(ANOTHER_USER);
        loggedInUser = ANOTHER_USER;

        List<Trip> result = new TestableTripService().getTripsByUser(user);

        isTrue(result.size() == 2);
    }

    @Test
    public void whenTheUserIsNotLoggedInAndHasFriendThenThrowAnException() {
        loggedInUser = GUEST_USER;

        assertThrows(UserNotLoggedInException.class, () -> new TestableTripService().getTripsByUser(new User()));
    }

    private class TestableTripService extends TripService {
        protected User loggedUser() {
            return loggedInUser;
        }

        protected List<Trip> userTrips(User user) {
            return asList(TRIP, TRIP);
        }
    }

}
