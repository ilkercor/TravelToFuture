package com.amadeusproject.TravelToFuture.Service.Airport.Impl;

import com.amadeusproject.TravelToFuture.DataAccess.Airport.AirportRepository;
import com.amadeusproject.TravelToFuture.Entities.Airport;
import com.amadeusproject.TravelToFuture.Service.Airport.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Long id, Airport airport) {
        if (airportRepository.existsById(id)) {
            airport.setId(id);
            return airportRepository.save(airport);
        }
        return null;
    }

    @Override
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

}
