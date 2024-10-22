package com.github.bernabaris.flightsearchapi.controller;

import com.github.bernabaris.flightsearchapi.dto.AirportDto;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.service.AirportService;
import com.github.bernabaris.flightsearchapi.util.Converter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {this.airportService = airportService;}

    @PostMapping("/add")
    public ResponseEntity<AirportDto> addAirport(@Valid @RequestBody AirportDto airportDto) {
        Airport savedAirport = airportService.addAirport(Converter.airportDtoToModel(airportDto));
        return ResponseEntity.ok(Converter.airportModelToDto(savedAirport));
    }

    @PostMapping("/add/list")
    public ResponseEntity<List<AirportDto>> addAirports(@Valid @RequestBody List<AirportDto> airportDtos) {
        List<Airport> airports = airportDtos.stream().map(Converter::airportDtoToModel).toList();
        List<Airport> savedAirports = airportService.addAirports(airports);
        return ResponseEntity.ok(savedAirports.stream().map(Converter::airportModelToDto).toList());
    }

    @GetMapping("/allFlights")
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        List<AirportDto> airportDtos = airportService.getAllAirports()
                .stream()
                .map(Converter::airportModelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(airportDtos);
    }
}