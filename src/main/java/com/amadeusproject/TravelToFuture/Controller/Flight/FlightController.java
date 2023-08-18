package com.amadeusproject.TravelToFuture.Controller.Flight;


import com.amadeusproject.TravelToFuture.Entities.Flight;
import com.amadeusproject.TravelToFuture.Service.Flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {


    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id).orElse(null);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam LocalDateTime departureDateTime,
            @RequestParam(required = false) LocalDateTime arrivalDateTime) {

        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, departureDateTime, arrivalDateTime);
        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.ok(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

}
