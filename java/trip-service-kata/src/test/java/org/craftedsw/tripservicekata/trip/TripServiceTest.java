package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    @Test
    void retrieveTripsForUser() {
        Mockito.mockStatic(UserSession.class);
        Mockito.mockStatic(TripDAO.class);
        UserSession userSession = mock(UserSession.class);
        User loggedUser = new User();
        User user = new User();
        user.addFriend(loggedUser);
        when(UserSession.getInstance()).thenReturn(userSession);
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        when(TripDAO.findTripsByUser(user)).thenReturn(new ArrayList<>(Collections.singletonList(new Trip())));

        List<Trip> result = new TripService().getTripsByUser(user);

        assertThat(result.size(), equalTo(1));
    }
}
