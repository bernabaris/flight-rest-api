package com.github.bernabaris.flightsearchapi.service;

import com.github.bernabaris.flightsearchapi.entity.AirportEntity;
import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.repository.AirportRepository;
import com.github.bernabaris.flightsearchapi.repository.FlightRepository;
import com.github.bernabaris.flightsearchapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight addFlight(Flight flight) {
        FlightEntity flightEntity = flightRepository.save(Converter.flightModelToEntity(flight));
        return Converter.flightEntityToModel(flightRepository.findById(flightEntity.getId()).get());
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll().stream().map(Converter::flightEntityToModel).toList();
    }

    public List<Flight> addFlights(List<Flight> flights) {
        List<FlightEntity> flightEntities = flights.stream().map(Converter::flightModelToEntity).toList();
        List<FlightEntity> savedFlights = flightRepository.saveAll(flightEntities);
        return savedFlights.stream().map(Converter::flightEntityToModel).toList();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).stream().map(Converter::flightEntityToModel).toList().get(0);
    }

    public Flight updateFlight(Flight flight, Long id) {
        FlightEntity flightEntity = flightRepository.findById(id).orElse(null);
        flightEntity.setId(flight.getId());
        flightEntity.setDepartureAirport(Converter.airportModelToEntity(flight.getDepartureAirport()));
        flightEntity.setArrivalAirport(Converter.airportModelToEntity(flight.getArrivalAirport()));
        flightEntity.setDepartureTime(flight.getDepartureTime());
        flightEntity.setArrivalTime(flight.getArrivalTime());
        flightEntity.setPrice(flight.getPrice());
        flightRepository.save(flightEntity);
        return Converter.flightEntityToModel(flightEntity);
    }
}



