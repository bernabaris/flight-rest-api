package com.github.bernabaris.flightsearchapi;

import com.github.bernabaris.flightsearchapi.model.Airport;
import com.github.bernabaris.flightsearchapi.model.Flight;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FlightRestApiTest {
    @Autowired
    private FlightService flightService;
    @Autowired
    private AirportService airportService;

    Airport istanbulAirport = new Airport(null, "Istanbul");
    Airport ankaraAirport = new Airport(null, "Ankara");
    Airport kutahyaAirport = new Airport(null, "Kutahya");

    Flight istAnkFlight = new Flight(null,
            istanbulAirport,
            ankaraAirport,
            LocalDateTime.parse("2024-10-20T14:30:00"),
            LocalDateTime.parse("2024-10-20T17:30:00"),
            299.99);

    Flight istKutFlight = new Flight(null,
            istanbulAirport,
            kutahyaAirport,
            LocalDateTime.parse("2024-10-10T12:30:00"),
            LocalDateTime.parse("2024-10-15T17:30:00"),
            399.99);

    @Test
    public void test1AddFlight() {
        istanbulAirport = airportService.addAirport(istanbulAirport);
        ankaraAirport = airportService.addAirport(ankaraAirport);
        kutahyaAirport = airportService.addAirport(kutahyaAirport);

        istAnkFlight.setDepartureAirport(istanbulAirport);;
        istAnkFlight.setArrivalAirport(ankaraAirport);

        istKutFlight.setDepartureAirport(kutahyaAirport);
        istKutFlight.setArrivalAirport(istanbulAirport);

        Flight result = flightService.addFlight(istAnkFlight);
        assert (result.getDepartureAirport().equals(istanbulAirport));
        assert (result.getArrivalAirport().equals(ankaraAirport));
        assert (result.getDepartureTime().equals(istAnkFlight.getDepartureTime()));
        assert (result.getArrivalTime().equals(istAnkFlight.getArrivalTime()));
        assert (result.getPrice() == istAnkFlight.getPrice());
        istAnkFlight = result;
    }

    @Test
    public void test2FindFlight() {
        List<Flight> flights = flightService.getAllFlights();
        log.info(flights.toString());
        assert (flights.size() == 1);
    }

    @Test
    public void test4SearchFlights() {

    }

}
