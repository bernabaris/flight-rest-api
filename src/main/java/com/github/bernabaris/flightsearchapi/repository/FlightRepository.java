package com.github.bernabaris.flightsearchapi.repository;

import com.github.bernabaris.flightsearchapi.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
}
