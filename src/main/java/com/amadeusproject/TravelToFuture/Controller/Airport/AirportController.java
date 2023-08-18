package com.amadeusproject.TravelToFuture.Controller.Airport;


import com.amadeusproject.TravelToFuture.Entities.Airport;
import com.amadeusproject.TravelToFuture.Service.Airport.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id).orElse(null);
        if (airport != null) {
            return ResponseEntity.ok(airport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return ResponseEntity.ok(createdAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        Airport updatedAirport = airportService.updateAirport(id, airport);
        if (updatedAirport != null) {
            return ResponseEntity.ok(updatedAirport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
