package com.group46.tests;

import com.group46.components.Dog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DogTest {

    @Test
    void testDogConstructor() {
        Dog dog = new Dog("Taz", 100, 30, 85, 100);

        assertEquals("Taz", dog.getName(), "Dog's name should be 'Taz'");
        assertEquals(100, dog.getHealth(), "Dog's health should be 100");
        assertEquals(30, dog.getFullness(), "Dog's fullness should be 30");
        assertEquals(85, dog.getHappiness(), "Dog's happiness should be 85");
        assertEquals(100, dog.getEnergy(), "Dog's energy should be 100");
    }
}
