package com.group46.tests;

import com.group46.components.Fish;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FishTest {

    @Test
    void testFishConstructor() {
        Fish fish = new Fish("Nemo", 76, 55, 100, 68);

        assertEquals("Nemo", fish.getName(), "Fish's name should be 'Nemo'");
        assertEquals(76, fish.getHealth(), "Fish's health should be 76");
        assertEquals(55, fish.getFullness(), "Fish's fullness should be 55");
        assertEquals(100, fish.getHappiness(), "Fish's happiness should be 100");
        assertEquals(68, fish.getEnergy(), "Fish's energy should be 68");
    }
}
