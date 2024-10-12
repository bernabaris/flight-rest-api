package com.github.bernabaris.flightsearchapi.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Flight {
    private long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
}
