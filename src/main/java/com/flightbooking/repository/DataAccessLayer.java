package com.flightbooking.repository;

import java.util.List;
import com.flightbooking.model.*;

public interface DataAccessLayer {

    String createFlight(Flight flight);
    List<Flight> getAllFlights();
    Flight getFlightByName(String name);
    String deleteFlight(String name);

}
