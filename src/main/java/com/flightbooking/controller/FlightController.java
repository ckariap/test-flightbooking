package com.flightbooking.controller;

import java.util.List;
import com.flightbooking.model.Flight;
import com.flightbooking.repository.DataAccessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/flights")
public class FlightController {

    @Autowired
    private DataAccessLayer flightService;
 
    @PostMapping(value = "")
    public String createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @GetMapping(value = "")
    public List<Flight> getFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{flight_number}")
    public Flight getFlight(@PathVariable("flight_number") String flightNumber) {
        return flightService.getFlightByName(flightNumber);
    }

    @DeleteMapping(value = "/{flight_number}") 
    public String deleteFlight(@PathVariable("flight_number") String flightNumber) {
        return flightService.deleteFlight(flightNumber);
    }

}




