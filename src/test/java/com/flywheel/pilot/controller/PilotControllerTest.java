package com.flywheel.pilot.controller;

import com.flywheel.pilot.model.Pilot;
import com.flywheel.pilot.repository.PilotRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PilotController.class)
class PilotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PilotRepository pilotRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("GET /api/pilots")
    class GetAllPilotsTests {

        @Test
        @DisplayName("should return empty list when no pilots exist")
        void shouldReturnEmptyListWhenNoPilotsExist() throws Exception {
            when(pilotRepository.findAll()).thenReturn(Collections.emptyList());

            mockMvc.perform(get("/api/pilots"))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                   .andExpect(content().json("[]"));
        }

        @Test
        @DisplayName("should return list of pilots when pilots exist")
        void shouldReturnListOfPilotsWhenPilotsExist() throws Exception {
            Pilot pilot1 = new Pilot("John Doe", 40);
            Pilot pilot2 = new Pilot("Jane Doe", 35);
            when(pilotRepository.findAll()).thenReturn(Arrays.asList(pilot1, pilot2));

            mockMvc.perform(get("/api/pilots"))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$[0].name").value("John Doe"))
                   .andExpect(jsonPath("$[0].hoursLeftThisWeek").value(40))
                   .andExpect(jsonPath("$[1].name").value("Jane Doe"))
                   .andExpect(jsonPath("$[1].hoursLeftThisWeek").value(35));
        }
    }

    @Nested
    @DisplayName("POST /api/pilots")
    class AddPilotTests {

        @Test
        @DisplayName("should create new pilot successfully")
        void shouldCreateNewPilotSuccessfully() throws Exception {
            Pilot inputPilot = new Pilot("New Pilot", 40);
            when(pilotRepository.save(any(Pilot.class))).thenReturn(inputPilot);

            mockMvc.perform(post("/api/pilots")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(inputPilot)))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$.name").value("New Pilot"))
                   .andExpect(jsonPath("$.hoursLeftThisWeek").value(40));
        }

        @Test
        @DisplayName("should handle empty pilot request")
        void shouldHandleEmptyPilotRequest() throws Exception {
            mockMvc.perform(post("/api/pilots")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content("{}"))
                   .andExpect(status().isOk());
        }
    }
}
