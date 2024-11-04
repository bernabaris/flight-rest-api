package com.github.bernabaris.flightsearchapi.repository;

import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import com.github.bernabaris.flightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query("SELECT f FROM FlightEntity f " +
            "WHERE f.departureAirport.id = :departureCode " +
            "AND f.arrivalAirport.id = :arrivalCode " +
            "AND f.departureTime >= :startTime " +
            "AND f.arrivalTime < :endTime")
    List<Flight> searchFlights(
            @Param("departureCode") String departureCode,
            @Param("arrivalCode") String arrivalCode,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

}
