package com.github.bernabaris.flightsearchapi.service;

import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.repository.FlightRepository;
import com.github.bernabaris.flightsearchapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight addFlight(Flight flight) {
        FlightEntity flightEntity = flightRepository.save(Converter.flightModelToEntity(flight));
        return Converter.flightEntityToModel(flightEntity);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll().stream().map(Converter::flightEntityToModel).toList();
    }

}
