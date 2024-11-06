package com.github.bernabaris.flightsearchapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.bernabaris.flightsearchapi.dto.FlightDto;
import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.model.Flights;
import com.github.bernabaris.flightsearchapi.repository.FlightRepository;
import com.github.bernabaris.flightsearchapi.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class MockFlightService {

    @Autowired
    private FlightRepository flightRepository;

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
