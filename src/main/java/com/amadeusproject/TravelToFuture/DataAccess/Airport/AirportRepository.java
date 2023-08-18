package com.amadeusproject.TravelToFuture.DataAccess.Airport;

import com.amadeusproject.TravelToFuture.Entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findByCity(String city);
}
