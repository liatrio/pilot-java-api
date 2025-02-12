package com.flywheel.pilot.repository;

import com.flywheel.pilot.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
