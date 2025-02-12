package com.flywheel.pilot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int hoursLeftThisWeek;

    public Pilot() {}

    public Pilot(String name, int hoursLeftThisWeek) {
        this.name = name;
        this.hoursLeftThisWeek = hoursLeftThisWeek;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursLeftThisWeek() {
        return hoursLeftThisWeek;
    }

    public void setHoursLeftThisWeek(int hoursLeftThisWeek) {
        this.hoursLeftThisWeek = hoursLeftThisWeek;
    }
}
