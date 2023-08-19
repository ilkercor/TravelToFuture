package com.amadeusproject.TravelToFuture.ScheduledJob;

import com.amadeusproject.TravelToFuture.DataAccess.Flight.FlightRepository;
import com.amadeusproject.TravelToFuture.Entities.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob {

    private final FlightRepository flightRepository;


    @Autowired
    public ScheduledJob(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchFlightInfoAndSaveToDatabase() {

        // Mock API request
        String mockApiResponse = mockApiRequest();

        Flight flight = processApiResponse(mockApiResponse);

        flightRepository.save(flight);


        System.out.println("Scheduled job performed");
    }


    private String mockApiRequest(){
        String mockApiResponse = "{"
                + "\"arrivalAirportId\": 2,"
                + "\"arrivalDateTime\": \"2023-08-19T16:30:00\","
                + "\"departureAirportId\": 1,"
                + "\"departureDateTime\": \"2023-08-19T12:30:00\","
                + "\"price\": 2500"
                + "}";

        return mockApiResponse;
    }

    private Flight processApiResponse(String apiResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);

        try {
            // JSON verisini çözümleme ve FlightInfo nesnesini doldurma
            Flight flightInfo = objectMapper.readValue(apiResponse, Flight.class);
            return flightInfo;
        } catch (Exception e) {
            // JSON çözümleme hatası
            e.printStackTrace();
            return null; // veya isteğe bağlı bir hata durumu işleme
        }

    }

}
