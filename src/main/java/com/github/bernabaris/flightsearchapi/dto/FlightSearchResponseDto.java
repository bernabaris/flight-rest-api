package com.github.bernabaris.flightsearchapi.dto;

import java.util.ArrayList;
import java.util.List;

public class FlightSearchResponseDto {
    private List<FlightDto> departureFlights = new ArrayList<>();
    private List<FlightDto> arrivalFlights = new ArrayList<>();
}
