package com.flywheel.pilot.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.assertThat;

class PilotTest {

    @Nested
    @DisplayName("setName tests")
    class SetNameTests {
        
        @Test
        @DisplayName("should set and get name correctly")
        void shouldSetAndGetName() {
            // Arrange
            Pilot pilot = new Pilot();
            String expectedName = "John Doe";

            // Act
            pilot.setName(expectedName);

            // Assert
            assertThat(pilot.getName()).isEqualTo(expectedName);
        }
    }
}