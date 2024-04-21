package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = loggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = userTrips(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected User loggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> userTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
