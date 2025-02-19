package com.flywheel.pilot.repository;

import com.flywheel.pilot.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Basic CRUD operations are automatically implemented by JPA
}