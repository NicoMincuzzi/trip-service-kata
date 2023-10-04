package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (!UserSession.getInstance().isLogged()) {
            throw new UserNotLoggedInException();
        }

        if (isFriend(user)) {
            return TripDAO.findTripsByUser(user);
        }

        return new ArrayList<>();
    }

    private static boolean isFriend(User user) {
        User loggedUser = UserSession.getInstance().getLoggedUser();
        return user.getFriends().stream().anyMatch(it -> it.equals(loggedUser));
    }

}
