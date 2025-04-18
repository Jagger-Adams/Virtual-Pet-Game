package com.group46.tests;

import com.group46.components.pet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PetClassTest {
    @Test
    public void petConstructorTest() {
        pet pet = new pet ("fish", "Bill", 75, 85, 95, 100);
        assertEquals("Bill", pet.getName());
        assertEquals(75, pet.getHealth());
        assertEquals(85, pet.getFullness());
        assertEquals(95, pet.getHappiness());
        assertEquals(100, pet.getEnergy());
    }

    @Test
    public void petHungryStateTest() {
        pet pet = new pet ("fish", "Bill", 100, 0, 100, 100);
        assertEquals("hungry", pet.getState());
    }

    @Test
    public void petAngryStateTest() {
        pet pet = new pet ("fish", "Bill", 100, 100, 0, 100);
        assertEquals("angry", pet.getState());
    }

    @Test
    public void petSleepingStateTest() {
        pet pet = new pet ("fish", "Bill", 100, 100, 100, 0);
        assertEquals("sleeping", pet.getState());
    }

    @Test
    public void petDeadStateTest() {
        pet pet = new pet ("fish", "Bill", 0, 100, 100, 100);
        assertEquals("dead", pet.getState());
    }

    @Test
    public void petExerciseTest() {
        pet pet = new pet ("fish", "Bill", 75, 75, 75, 75);
        pet.exercise();
        assertEquals(65, pet.getEnergy());
        assertEquals(65, pet.getFullness());
        assertEquals(100, pet.getHappiness());
    }

    @Test
    public void petPlayTest() {
        pet pet = new pet ("fish", "Bill", 75, 75, 75, 75);
        pet.play();
        assertEquals(85, pet.getHappiness());
    }

    @Test
    public void petVetTest() {
        pet pet = new pet ("fish", "Bill", 75, 75, 75, 75);
        pet.vet();
        assertEquals(100, pet.getHealth());
    }
}
