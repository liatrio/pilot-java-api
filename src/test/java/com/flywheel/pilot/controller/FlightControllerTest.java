package com.flywheel.pilot.controller;

import com.flywheel.pilot.model.Flight;
import com.flywheel.pilot.repository.FlightRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("GET /api/flights")
    class GetAllFlightsTests {

        @Test
        @DisplayName("should return list of flights when flights exist")
        void shouldReturnListOfFlightsWhenFlightsExist() throws Exception {
            Flight flight1 = new Flight("SF101", "SFO", "LAX", "2024-02-21T10:00:00Z");
            Flight flight2 = new Flight("LA202", "LAX", "NYC", "2024-02-21T14:00:00Z");
            when(flightRepository.findAll()).thenReturn(Arrays.asList(flight1, flight2));

            mockMvc.perform(get("/api/flights")
                   .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$[0].flightNumber").value("SF101"))
                   .andExpect(jsonPath("$[0].origin").value("SFO"))
                   .andExpect(jsonPath("$[0].destination").value("LAX"))
                   .andExpect(jsonPath("$[0].scheduledTime").value("2024-02-21T10:00:00Z"))
                   .andExpect(jsonPath("$[1].flightNumber").value("LA202"))
                   .andExpect(jsonPath("$[1].origin").value("LAX"))
                   .andExpect(jsonPath("$[1].destination").value("NYC"))
                   .andExpect(jsonPath("$[1].scheduledTime").value("2024-02-21T14:00:00Z"));
        }

        @Test
        @DisplayName("should return empty list when no flights exist")
        void shouldReturnEmptyListWhenNoFlightsExist() throws Exception {
            when(flightRepository.findAll()).thenReturn(Collections.emptyList());

            mockMvc.perform(get("/api/flights")
                   .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$").isArray())
                   .andExpect(jsonPath("$").isEmpty());
        }
    }

    @Nested
    @DisplayName("POST /api/flights")
    class CreateFlightTests {

        @Test
        @DisplayName("should create new flight when valid data is provided")
        void shouldCreateNewFlightWhenValidDataProvided() throws Exception {
            Flight newFlight = new Flight("SF101", "SFO", "LAX", "2024-02-21T10:00:00Z");
            when(flightRepository.save(any(Flight.class))).thenReturn(newFlight);

            mockMvc.perform(post("/api/flights")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(newFlight)))
                    .andExpect(status().isCreated());
        }
    }
}