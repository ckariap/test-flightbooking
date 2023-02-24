package com.flightbooking.repository;

import com.flightbooking.model.Flight;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight,String> {
    
}