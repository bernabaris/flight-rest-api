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
            "WHERE f.departureAirport.id = :departureId " +
            "AND f.arrivalAirport.id = :arrivalId " +
            "AND f.departureTime >= :startTime " +
            "AND f.arrivalTime < :endTime")
    List<FlightEntity> searchFlights(
            @Param("departureId") Long departureId,
            @Param("arrivalId") Long arrivalId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

}
