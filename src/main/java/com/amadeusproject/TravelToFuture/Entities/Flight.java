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

    @Column(name = "flightId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departureAirportId")
    private Long departureAirportId;

    @Column(name = "arrivalAirportId")
    private Long arrivalAirportId;

    @Column(name = "departureDateTime")
    private LocalDateTime departureDateTime;

    @Column(name = "arrivalDateTime")
    private LocalDateTime arrivalDateTime;

    @Column(name = "price")
    private double price;

}
