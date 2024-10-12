package com.github.bernabaris.flightsearchapi.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FlightDto {
    private Long id;
    private AirportDto departureAirport;
    private AirportDto arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
}
