package com.github.bernabaris.flightsearchapi.util;

import com.github.bernabaris.flightsearchapi.dto.AirportDto;
import com.github.bernabaris.flightsearchapi.dto.FlightDto;
import com.github.bernabaris.flightsearchapi.entity.AirportEntity;
import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.model.Flight;

public class Converter {
    public static FlightEntity flightModelToEntity(Flight flight){
        FlightEntity flightEntity = new FlightEntity();
        if(flight.getId() != null){
            flightEntity.setId(flight.getId());
        }
        flightEntity.setDepartureAirport(airportModelToEntity(flight.getDepartureAirport()));
        flightEntity.setArrivalAirport(airportModelToEntity(flight.getArrivalAirport()));
        flightEntity.setDepartureTime(flight.getDepartureTime());
        flightEntity.setArrivalTime(flight.getArrivalTime());
        flightEntity.setPrice(flight.getPrice());
        return flightEntity;
    }

    public static AirportEntity airportModelToEntity(Airport airport){
        AirportEntity airportEntity = new AirportEntity();
        if(airport.getId() != null) {
            airportEntity.setId(airport.getId());
        }
        airportEntity.setCityName(airport.getCityName());
        return airportEntity;
    }

    public static Flight flightEntityToModel(FlightEntity flightEntity){
        Flight flight = new Flight();
        flight.setId(flightEntity.getId());
        flight.setDepartureAirport(airportEntityToModel(flightEntity.getDepartureAirport()));
        flight.setArrivalAirport(airportEntityToModel(flightEntity.getArrivalAirport()));
        flight.setDepartureTime(flightEntity.getDepartureTime());
        flight.setArrivalTime(flightEntity.getArrivalTime());
        flight.setPrice(flightEntity.getPrice());
        return flight;
    }

    public static Airport airportEntityToModel(AirportEntity airportEntity){
        Airport airport = new Airport();
        airport.setId(airportEntity.getId());
        airport.setCityName(airportEntity.getCityName());
        return airport;
    }

    public static Flight flightDtoToModel(FlightDto flightDto){
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setDepartureAirport(airportDtoToModel(flightDto.getDepartureAirport()));
        flight.setArrivalAirport(airportDtoToModel(flightDto.getArrivalAirport()));
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setPrice(flightDto.getPrice());
        return flight;
    }

    public static Airport airportDtoToModel(AirportDto airportDto){
        Airport airport = new Airport();
        airport.setId(airportDto.getId());
        airport.setCityName(airportDto.getCityName());
        return airport;
    }

    public static FlightDto flightModelToDto(Flight flight){
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setDepartureAirport(airportModelToDto(flight.getDepartureAirport()));
        flightDto.setArrivalAirport(airportModelToDto(flight.getArrivalAirport()));
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setPrice(flight.getPrice());
        return flightDto;
    }

    public static AirportDto airportModelToDto(Airport airport){
        AirportDto airportDto = new AirportDto();
        airportDto.setId(airport.getId());
        airportDto.setCityName(airport.getCityName());
        return airportDto;
    }
}