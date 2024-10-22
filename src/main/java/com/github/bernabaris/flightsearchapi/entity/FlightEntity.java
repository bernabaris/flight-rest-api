package com.github.bernabaris.flightsearchapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Flight")
@Data
@NoArgsConstructor
public class FlightEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     @ManyToOne
     @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
     private AirportEntity departureAirport;

     @ManyToOne
     @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
     private AirportEntity arrivalAirport;

     @Column(name = "departure_time")
     private LocalDateTime departureTime;

     @Column(name = "arrival_time")
     private LocalDateTime arrivalTime;

     @Column(name = "price")
     private double price;
}