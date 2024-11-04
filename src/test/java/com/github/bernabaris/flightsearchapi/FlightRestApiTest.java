package com.github.bernabaris.flightsearchapi;

import com.github.bernabaris.flightsearchapi.dto.FlightSearchInputDto;
import com.github.bernabaris.flightsearchapi.dto.FlightSearchResponseDto;
import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.service.AirportService;
import com.github.bernabaris.flightsearchapi.service.FlightService;
import com.github.bernabaris.flightsearchapi.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FlightRestApiTest {
    @Autowired
    private FlightService flightService;
    @Autowired
    private AirportService airportService;

    Airport istanbulAirport = new Airport(null, "Istanbul");
    Airport ankaraAirport = new Airport(null, "Ankara");
    Airport kutahyaAirport = new Airport(null, "Kutahya");
    Airport tekirdagAirport = new Airport(null, "Tekirdag");
    Airport samsunAirport = new Airport(null, "Samsun");
    Airport bursaAirport = new Airport(null, "Bursa");

    Flight istAnkFlight1 = new Flight(null,
            istanbulAirport,
            ankaraAirport,
            LocalDateTime.parse("2024-10-20T14:30:00"),
            LocalDateTime.parse("2024-10-20T17:30:00"),
            299.99);

    Flight istAnkFlight2 = new Flight(null,
            istanbulAirport,
            ankaraAirport,
            LocalDateTime.parse("2024-10-20T19:30:00"),
            LocalDateTime.parse("2024-10-20T21:30:00"),
            299.99);

    Flight istAnkFlight3 = new Flight(null,
            istanbulAirport,
            ankaraAirport,
            LocalDateTime.parse("2024-10-21T11:00:00"),
            LocalDateTime.parse("2024-10-21T18:30:00"),
            499.99);

    Flight istKutFlight = new Flight(null,
            istanbulAirport,
            kutahyaAirport,
            LocalDateTime.parse("2024-10-10T12:30:00"),
            LocalDateTime.parse("2024-10-15T17:30:00"),
            399.99);

    Flight istBursaFlight = new Flight(null,
            istanbulAirport,
            bursaAirport,
            LocalDateTime.parse("2024-10-11T11:30:00"),
            LocalDateTime.parse("2024-10-12T10:30:00"),
            499.99);

    @Test
    public void test1AddFlight() {
        istanbulAirport = airportService.addAirport(istanbulAirport);
        ankaraAirport = airportService.addAirport(ankaraAirport);
        kutahyaAirport = airportService.addAirport(kutahyaAirport);


        istAnkFlight1.setDepartureAirport(istanbulAirport);
        istAnkFlight1.setArrivalAirport(ankaraAirport);

        istKutFlight.setDepartureAirport(kutahyaAirport);
        istKutFlight.setArrivalAirport(istanbulAirport);

        Flight result = flightService.addFlight(istAnkFlight1);
        assert (result.getDepartureAirport().equals(istanbulAirport));
        assert (result.getArrivalAirport().equals(ankaraAirport));
        assert (result.getDepartureTime().equals(istAnkFlight1.getDepartureTime()));
        assert (result.getArrivalTime().equals(istAnkFlight1.getArrivalTime()));
        assert (result.getPrice() == istAnkFlight1.getPrice());
        istAnkFlight1 = result;
    }

    @Test
    public void test2FindFlight() {
        List<Flight> flights = flightService.getAllFlights();
        log.info(flights.toString());
        assert (flights.size() == 1);
    }

    @Test
    public void test3SearchFlights() {

        istanbulAirport = airportService.addAirport(istanbulAirport);
        ankaraAirport = airportService.addAirport(ankaraAirport);
        kutahyaAirport = airportService.addAirport(kutahyaAirport);
        tekirdagAirport = airportService.addAirport(tekirdagAirport);
        samsunAirport = airportService.addAirport(samsunAirport);
        bursaAirport = airportService.addAirport(bursaAirport);

        istAnkFlight1.setDepartureAirport(istanbulAirport);
        istAnkFlight1.setArrivalAirport(ankaraAirport);

        istKutFlight.setDepartureAirport(istanbulAirport);
        istKutFlight.setArrivalAirport(kutahyaAirport);

        istBursaFlight.setDepartureAirport(istanbulAirport);
        istBursaFlight.setArrivalAirport(bursaAirport);

        istAnkFlight2.setDepartureAirport(istanbulAirport);
        istAnkFlight2.setArrivalAirport(ankaraAirport);

        istAnkFlight3.setDepartureAirport(istanbulAirport);
        istAnkFlight3.setArrivalAirport(ankaraAirport);

        istAnkFlight1 = flightService.addFlight(istAnkFlight1);
        istKutFlight = flightService.addFlight(istKutFlight);
        istBursaFlight = flightService.addFlight(istBursaFlight);
        istAnkFlight2 = flightService.addFlight(istAnkFlight2);
        istAnkFlight3 = flightService.addFlight(istAnkFlight3);

        FlightSearchInputDto flightSearchInputDto = new FlightSearchInputDto();
        flightSearchInputDto.setDepartureAirport(Converter.airportModelToDto(istanbulAirport));
        flightSearchInputDto.setArrivalAirport(Converter.airportModelToDto(ankaraAirport));
        flightSearchInputDto.setDepartureDate(LocalDate.parse("2024-10-20"));
        flightSearchInputDto.setReturnDate(LocalDate.parse("2024-10-21"));
        FlightSearchResponseDto flightSearchResponseDto = flightService.searchFlight(flightSearchInputDto);

        assertThat(flightSearchResponseDto.getDepartureFlights().size()).isEqualTo(2);
        assertThat(flightSearchResponseDto.getReturnFlights().size()).isEqualTo(1);

    }

}
