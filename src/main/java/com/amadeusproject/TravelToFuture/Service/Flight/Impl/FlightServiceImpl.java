package com.amadeusproject.TravelToFuture.Service.Flight.Impl;

import com.amadeusproject.TravelToFuture.DataAccess.Flight.FlightRepository;
import com.amadeusproject.TravelToFuture.Entities.Flight;
import com.amadeusproject.TravelToFuture.Service.Flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        if (arrivalDateTime == null) {
            // Tek yönlü uçuş
            return flightRepository.findByDepartureCityAndArrivalCityAndDepartureDateTimeGreaterThanEqual(
                    departureCity, arrivalCity, departureDateTime);
        } else {
            // Çift yönlü uçuş
            List<Flight> outboundFlights = flightRepository.findByDepartureCityAndArrivalCityAndDepartureDateTimeGreaterThanEqual(
                    departureCity, arrivalCity, departureDateTime);
            List<Flight> returnFlights = flightRepository.findByDepartureCityAndArrivalCityAndDepartureDateTimeGreaterThanEqual(
                    arrivalCity, departureCity, arrivalDateTime);

            List<Flight> combinedFlights = new ArrayList<>();
            combinedFlights.addAll(outboundFlights);
            combinedFlights.addAll(returnFlights);

            return combinedFlights;
        }
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, Flight flight) {
        if (flightRepository.existsById(id)) {
            flight.setId(id);
            return flightRepository.save(flight);
        }
        return null;
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

}
