package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = loggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        boolean isFriend = user.hasAtLeastOneFriend(loggedUser);

        return (isFriend) ? userTrips(user) : emptyList();
    }

    protected User loggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> userTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
