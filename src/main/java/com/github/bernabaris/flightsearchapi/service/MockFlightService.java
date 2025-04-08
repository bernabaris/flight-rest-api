package com.github.bernabaris.flightsearchapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.bernabaris.flightsearchapi.dto.FlightDto;
import com.github.bernabaris.flightsearchapi.entity.AirportEntity;
import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.model.Flights;
import com.github.bernabaris.flightsearchapi.repository.AirportRepository;
import com.github.bernabaris.flightsearchapi.repository.FlightRepository;
import com.github.bernabaris.flightsearchapi.util.Converter;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MockFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @PostConstruct
    public void saveMockDataOnStartup() {
        // Save mock airports
        saveMockAirports();

        // Save mock flights
        saveMockFlightsFromFile();
    }

    public void saveMockAirports() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            // Read airport data from JSON file
            InputStream inputStream = new ClassPathResource("data/airports.json").getInputStream();
            List<Airport> airports = objectMapper.readValue(inputStream, new TypeReference<List<Airport>>() {});

            log.info("{}", airports.get(0));

            // Convert Airport models to AirportEntity
            List<AirportEntity> airportEntities = airports.stream()
                    .map(airport -> new AirportEntity(
                            airport.getId(),
                            airport.getCityName()))
                    .collect(Collectors.toList());

            // Save airport entities to the repository
            airportRepository.saveAll(airportEntities);
            log.info("Mock airport data saved to database.");
        } catch (IOException e) {
            System.err.println("Failed to read mock airport data: " + e.getMessage());
        }
    }

    public void saveMockFlightsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            InputStream inputStream = new ClassPathResource("data/flights2.json").getInputStream();
            List<Flight> flights = objectMapper.readValue(inputStream, new TypeReference<List<Flight>>() {});

            log.info("{}", flights.get(0));
            List<FlightEntity> flightEntities = flights.stream()
                    .map(Converter::flightModelToEntity)
                    .toList();

            flightRepository.saveAll(flightEntities);
            System.out.println("Mock flight data saved to database.");
        } catch (IOException e) {
            System.err.println("Failed to read mock flight data: " + e.getMessage());
        }
    }


}
