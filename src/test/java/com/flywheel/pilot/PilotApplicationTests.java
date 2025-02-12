package com.flywheel.pilot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.flywheel.pilot.model.Pilot;

class PilotApplicationTests {

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
}