package com.github.bernabaris.flightsearchapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchInputDto {
    private AirportDto departureAirport;
    private AirportDto arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}