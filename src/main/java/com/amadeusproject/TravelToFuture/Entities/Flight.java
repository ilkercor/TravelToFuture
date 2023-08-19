package com.amadeusproject.TravelToFuture.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "flights")
@Table(name = "flights")
public class Flight {

    @Column(name = "flight_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "arrival_airport_id")
    private Long arrivalAirportId;

    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;

    @Column(name = "departure_airport_id")
    private Long departureAirportId;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;


    @Column(name = "price")
    private double price;

}
