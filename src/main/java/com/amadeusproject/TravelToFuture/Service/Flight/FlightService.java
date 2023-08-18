package com.amadeusproject.TravelToFuture.Service.Flight;

import com.amadeusproject.TravelToFuture.Entities.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> getAllFlights();

    Optional<Flight> getFlightById(Long id);

    List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);

    Flight createFlight(Flight flight);

    Flight updateFlight(Long id, Flight flight);

    void deleteFlight(Long id);

}
