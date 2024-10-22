package com.github.bernabaris.flightsearchapi.controller;

import com.github.bernabaris.flightsearchapi.dto.FlightDto;
import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.service.FlightService;
import com.github.bernabaris.flightsearchapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto flightDto) {
        Flight savedFlight = flightService.addFlight(Converter.flightDtoToModel(flightDto));
        return ResponseEntity.ok(Converter.flightModelToDto(savedFlight));
    }

    @PostMapping("/add/list")
    public ResponseEntity<List<FlightDto>> addFlights(@RequestBody List<FlightDto> flightDtos) {
        List<Flight> flights = flightDtos.stream().map(Converter::flightDtoToModel).toList();
        List<Flight> savedFlights = flightService.addFlights(flights);
        return ResponseEntity.ok(savedFlights.stream().map(Converter::flightModelToDto).toList());
    }

    @GetMapping("/allFlights")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flightDtos = flightService.getAllFlights()
                .stream()
                .map(Converter::flightModelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flightDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        FlightDto flightDto = Converter.flightModelToDto(flight);
        return ResponseEntity.ok(flightDto);
    }
}