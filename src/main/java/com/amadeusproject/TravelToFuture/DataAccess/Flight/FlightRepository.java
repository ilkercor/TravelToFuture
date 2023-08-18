package com.amadeusproject.TravelToFuture.DataAccess.Flight;

import com.amadeusproject.TravelToFuture.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
