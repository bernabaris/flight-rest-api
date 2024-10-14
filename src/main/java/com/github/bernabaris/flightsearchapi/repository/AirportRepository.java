package com.github.bernabaris.flightsearchapi.repository;

import com.github.bernabaris.flightsearchapi.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AirportRepository extends JpaRepository<AirportEntity, Long> {
}
