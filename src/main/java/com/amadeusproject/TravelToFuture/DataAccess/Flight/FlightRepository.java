package com.amadeusproject.TravelToFuture.DataAccess.Flight;

import com.amadeusproject.TravelToFuture.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
            Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime);}
