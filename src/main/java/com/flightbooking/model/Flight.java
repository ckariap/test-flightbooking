package com.flightbooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Flight {

    @Id
    private String flightNumber;
    private String start;
    private String end;
    private String date;
    private String aircraft;

    public Flight() {

    }

    public Flight (String flightNum, String start, String end, String date, String aircraft) {
        this.flightNumber = flightNum;
        this.start = start;
        this.end = end;
        this.date = date;
        this.aircraft = aircraft;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }
    
    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    public String getDate() {
        return this.date;
    }

    public String getAircraft() {
        return this.aircraft;
    }

    public void setFlightNumber(String fligtNumber) {
        this.flightNumber = fligtNumber;
    }

}