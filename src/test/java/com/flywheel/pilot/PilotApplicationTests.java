package com.flywheel.pilot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flywheel.pilot.controller.PilotController;
import com.flywheel.pilot.model.Pilot;
import com.flywheel.pilot.repository.PilotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(PilotController.class)
public class PilotApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PilotRepository pilotRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void helloWorldTest() {
        assertEquals("Hello, World!", "Hello, World!");
    }

    @Test
    void testPilotConstructor() {
        Pilot pilot = new Pilot("John Doe", 10);
        assertEquals("John Doe", pilot.getName());
        assertEquals(10, pilot.getHoursLeftThisWeek());
    }

    @Test
    void testPilotSettersAndGetters() {
        Pilot pilot = new Pilot();
        pilot.setName("Jane Doe");
        pilot.setHoursLeftThisWeek(20);
        assertEquals("Jane Doe", pilot.getName());
        assertEquals(20, pilot.getHoursLeftThisWeek());
    }

    @Test
    void testGetAllPilots() throws Exception {
        List<Pilot> pilots = Arrays.asList(new Pilot("John Doe", 10), new Pilot("Jane Doe", 20));
        when(pilotRepository.findAll()).thenReturn(pilots);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pilots")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pilots)));
    }

    @Test
    void testAddPilot() throws Exception {
        Pilot pilot = new Pilot("John Doe", 10);
        when(pilotRepository.save(any(Pilot.class))).thenReturn(pilot);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pilots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pilot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("John Doe"))
                .andExpect(jsonPath("hoursLeftThisWeek").value(10));
    }
}