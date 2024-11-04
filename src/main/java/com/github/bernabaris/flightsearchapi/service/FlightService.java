package com.github.bernabaris.flightsearchapi.service;

import com.github.bernabaris.flightsearchapi.dto.FlightDto;
import com.github.bernabaris.flightsearchapi.dto.FlightSearchInputDto;
import com.github.bernabaris.flightsearchapi.dto.FlightSearchResponseDto;
import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.repository.FlightRepository;
import com.github.bernabaris.flightsearchapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Flight deleteFlight(Long id) {
        Optional<FlightEntity> flightEntity = flightRepository.findById(id);
        if (flightEntity.isPresent()) {
            flightRepository.deleteById(id);
            return Converter.flightEntityToModel(flightEntity.get());
        } else {
            throw new NoSuchElementException("Flight not found with id: " + id);
        }
    }

    public Flight updateFlight(Flight flight) {
        Optional<FlightEntity> flightEntity = flightRepository.findById(flight.getId());
        if (flightEntity.isPresent()) {
            flightRepository.save(Converter.flightModelToEntity(flight));
            return Converter.flightEntityToModel(flightEntity.get());
        } else {
            throw new NoSuchElementException("Flight not found with id: " + flight.getId());
        }
    }

  /*public Flight getFlightsBySearch(FlightSearchInputDto flightSearchInputDto) {
        FlightSearchResponseDto flightSearchResponseDto = new FlightSearchResponseDto();
        //departure flights always needed
        List<FlightDto> departureFlight = search

    }*/

    public FlightSearchResponseDto searchFlight(FlightSearchInputDto flightSearchInputDto) {
        FlightSearchResponseDto flightSearchResponseDto = new FlightSearchResponseDto();

        LocalDate departureDate = flightSearchInputDto.getDepartureDate();
        LocalDateTime departureStart = departureDate.atStartOfDay();
        LocalDateTime departureEnd = departureDate.atTime(LocalTime.MAX);

        List<FlightEntity> departureFlightEntities = flightRepository.searchFlights(
                flightSearchInputDto.getDepartureAirport().getId(),
                flightSearchInputDto.getArrivalAirport().getId(),
                departureStart,
                departureEnd);
        List<Flight> departureFlights = departureFlightEntities.stream().map(Converter::flightEntityToModel).toList();
        flightSearchResponseDto.setDepartureFlights(departureFlights.stream().map(Converter::flightModelToDto).toList());

        if (flightSearchInputDto.getReturnDate() != null) {
            LocalDateTime returnStart = flightSearchInputDto.getReturnDate().atStartOfDay();
            LocalDateTime returnEnd = flightSearchInputDto.getReturnDate().atTime(LocalTime.MAX);
            List<FlightEntity> returnFlightEntities = flightRepository.searchFlights(
                    flightSearchInputDto.getDepartureAirport().getId(),
                    flightSearchInputDto.getArrivalAirport().getId(),
                    returnStart,
                    returnEnd);
            List<Flight> returnFlights = returnFlightEntities.stream().map(Converter::flightEntityToModel).toList();
            flightSearchResponseDto.setReturnFlights(returnFlights.stream().map(Converter::flightModelToDto).toList());
        }
        return flightSearchResponseDto;

    }
}



