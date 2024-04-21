package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.util.Assert.isTrue;

public class TripServiceTest extends TripService {

    private static final User REGISTERED_USER = new User();
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

    private class TestableTripService extends TripService {
        protected User loggedUser() {
            return loggedInUser;
        }

        protected List<Trip> userTrips(User user) {
            return asList(TRIP, TRIP);
        }
    }

}
