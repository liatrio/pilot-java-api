package com.flywheel.pilot.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class FlightTest {

    @Nested
    @DisplayName("Flight properties tests")
    class FlightPropertiesTests {
        
        @Test
        @DisplayName("should set and get flight properties correctly")
        void shouldSetAndGetFlightPropertiesCorrectly() {
            // Arrange
            Flight flight = new Flight();
            String flightNumber = "FL123";
            String origin = "JFK";
            String destination = "LAX";
            LocalDateTime departureTime = LocalDateTime.now();
            LocalDateTime arrivalTime = departureTime.plusHours(5);
            Pilot pilot = new Pilot("John Doe", 40);

            // Act
            flight.setFlightNumber(flightNumber);
            flight.setOrigin(origin);
            flight.setDestination(destination);
            flight.setDepartureTime(departureTime);
            flight.setArrivalTime(arrivalTime);
            flight.setPilot(pilot);

            // Assert
            assertThat(flight.getFlightNumber()).isEqualTo(flightNumber);
            assertThat(flight.getOrigin()).isEqualTo(origin);
            assertThat(flight.getDestination()).isEqualTo(destination);
            assertThat(flight.getDepartureTime()).isEqualTo(departureTime);
            assertThat(flight.getArrivalTime()).isEqualTo(arrivalTime);
            assertThat(flight.getPilot()).isEqualTo(pilot);
        }
    }
}