package com.flightbooking.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.flightbooking.model.Flight;
import com.flightbooking.util.Aircrafts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class FlightService implements DataAccessLayer{

    @Autowired
    public MongoTemplate mongoTemplate;

    public String createFlight(Flight flight) {
        Flight newFlight = flight;
        String date = newFlight.getDate();
        String aircraft = newFlight.getAircraft();
        if((isValidDate(date)) && (isValidAircraft(aircraft))){
            newFlight.setFlightNumber(assignFlightNumber(newFlight.getStart(),newFlight.getEnd()));
            mongoTemplate.save(newFlight);
            return "v1/flights/"+newFlight.getFlightNumber();
        }
        return "Invalid Date or Aircraft type";
    }

    public List<Flight> getAllFlights() {
        return mongoTemplate.findAll(Flight.class);
    }

    public Flight getFlightByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("flightNumber").is(name));
        return mongoTemplate.findOne(query, Flight.class);
    }

    public String deleteFlight(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("flightNumber").is(name));
        Flight flight = mongoTemplate.findOne(query, Flight.class);
        mongoTemplate.remove(flight);
        return "Deleted Successfully";
    }
    

    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setLenient(false);
        try {
          dateFormat.parse(inDate.trim());
        } catch (ParseException exception) {
          return false;
        }
        return true;
    }

    public boolean isValidAircraft(String aircraft) {
        for (Aircrafts air : Aircrafts.values()) {
            if (air.name().equals(aircraft.replaceAll("[^a-zA-Z0-9]", ""))) {
                return true;
            }
        }
        return false;
    }
 
    public String assignFlightNumber(String start, String end) {
        String randomNum = String.format("%04d",ThreadLocalRandom.current().nextInt(999, 9999 + 1));
        return start.substring(0,1)+end.substring(0,1)+randomNum;
    }

}


