package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group46.controllers.DailyOpening;
import com.group46.controllers.Inventory;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    public void setup() {
        inventory = Inventory.getInventory();
        // Reset counts for testing
        inventory.setFood("common", 5);
        inventory.setFood("rare", 5);
        inventory.setFood("legendary", 5);
        inventory.setGifts("common", 5);
        inventory.setGifts("rare", 5);
        inventory.setGifts("legendary", 5);
    }

    @Test
    public void testUseFood() {
        int initial = inventory.getNumberOfFood("common");
        inventory.useFood("common");
        assertEquals(initial - 1, inventory.getNumberOfFood("common"), "Using common food should decrement count by 1");
    }

    @Test
    public void testAddGift() {
        int initial = inventory.getNumberOfGifts("common");
        inventory.addGift("common");
        assertEquals(initial + 1, inventory.getNumberOfGifts("common"), "Adding a common gift should increment count by 1");
    }

    @Test
    public void testSetAndGetFood() {
        inventory.setFood("rare", 10);
        assertEquals(10, inventory.getNumberOfFood("rare"), "Rare food should be set to 10");
    }

    @Test
    public void testSetAndGetGifts() {
        inventory.setGifts("legendary", 15);
        assertEquals(15, inventory.getNumberOfGifts("legendary"), "Legendary gifts should be set to 15");
    }
}
