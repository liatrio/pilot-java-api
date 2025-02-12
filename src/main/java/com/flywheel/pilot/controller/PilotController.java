package com.flywheel.pilot.controller;

import com.flywheel.pilot.model.Pilot;
import com.flywheel.pilot.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {
    
    @Autowired
    private PilotRepository pilotRepository;

    @GetMapping
    public List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pilot> addPilot(@RequestBody Pilot pilot) {
        Pilot savedPilot = pilotRepository.save(pilot);
        return ResponseEntity.ok(savedPilot);
    }
}
