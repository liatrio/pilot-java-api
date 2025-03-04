package com.flywheel.pilot.controller;

import com.flywheel.pilot.model.Flight;
import com.flywheel.pilot.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@Validated
public class FlightController {
    
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFlight(@Valid @RequestBody FlightRequest flightRequest) {
        // Assuming the flight creation logic is here
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public static class FlightRequest {
        private String flightData;

        // Getters and setters
        public String getFlightData() {
            return flightData;
        }

        public void setFlightData(String flightData) {
            this.flightData = flightData;
        }
    }
}