package com.github.bernabaris.flightsearchapi.service;

import com.github.bernabaris.flightsearchapi.entity.AirportEntity;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.repository.AirportRepository;
import com.github.bernabaris.flightsearchapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Airport addAirport(Airport airport) {
        AirportEntity airportEntity = airportRepository.save(Converter.airportModelToEntity(airport));
        return Converter.airportEntityToModel(airportEntity);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll().stream().map(Converter::airportEntityToModel).toList();
    }

    public List<Airport> addAirports(List<Airport> airports) {
        List<AirportEntity> airportEntities = airports.stream().map(Converter::airportModelToEntity).toList();
        List<AirportEntity> savedAirports = airportRepository.saveAll(airportEntities);
        return savedAirports.stream().map(Converter::airportEntityToModel).collect(Collectors.toList());
    }

}
