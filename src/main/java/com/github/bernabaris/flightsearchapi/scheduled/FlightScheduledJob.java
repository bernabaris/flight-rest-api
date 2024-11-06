package com.github.bernabaris.flightsearchapi.scheduled;
import com.github.bernabaris.flightsearchapi.model.Flight;
import com.github.bernabaris.flightsearchapi.service.MockFlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightScheduledJob {

    @Autowired
    private MockFlightService mockFlightService;

    @Scheduled(cron = "*/10 * * * * *")
    public void saveMockFlightsDaily() {
        mockFlightService.saveMockFlightsFromFile();
    }

}
