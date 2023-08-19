package com.amadeusproject.TravelToFuture.Service.Flight.Impl;

import com.amadeusproject.TravelToFuture.DataAccess.Airport.AirportRepository;
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

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
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
        Long departureAirportId = airportRepository.getAirportByCity(departureCity).getId();
        Long arrivalAirportId = airportRepository.getAirportByCity(arrivalCity).getId();

        if (arrivalDateTime == null) {
            // Tek yönlü uçuş
            return flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                    departureAirportId, arrivalAirportId, departureDateTime);
        } else {
            // Çift yönlü uçuş
            List<Flight> outboundFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                    departureAirportId, arrivalAirportId, departureDateTime);
            List<Flight> returnFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                    arrivalAirportId, departureAirportId, arrivalDateTime);

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
