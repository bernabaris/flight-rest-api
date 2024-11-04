package com.github.bernabaris.flightsearchapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchResponseDto {
    private List<FlightDto> departureFlights = new ArrayList<>();
    private List<FlightDto> returnFlights = new ArrayList<>();
}
