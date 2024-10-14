package com.github.bernabaris.flightsearchapi.controller;

import com.github.bernabaris.flightsearchapi.dto.AirportDto;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.service.AirportService;
import com.github.bernabaris.flightsearchapi.util.Converter;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {this.airportService = airportService;}

    @PostMapping("/add")
    public ResponseEntity<AirportDto> addAirport(@RequestBody AirportDto airportDto) {
        Airport savedAirport = airportService.addAirport(Converter.airportDtoToModel(airportDto));
        return ResponseEntity.ok(Converter.airportModelToDto(savedAirport));
    }
}