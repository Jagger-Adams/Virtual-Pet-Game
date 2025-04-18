package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.group46.controllers.DailyOpening;
import com.group46.controllers.Inventory;

/**
 * This integration test simulates a top-down scenario by calling DailyOpening.generateGift()
 * and verifying that it properly updates the Inventory module.
 */
public class IntegrationTestDailyAndInv {
    private Inventory inventory;

    @BeforeEach
    public void setup() {
        // Reset inventory counts for both food and gifts
        inventory = Inventory.getInventory();
        inventory.setFood("common", 25);
        inventory.setFood("rare", 25);
        inventory.setFood("legendary", 25);
        inventory.setGifts("common", 25);
        inventory.setGifts("rare", 25);
        inventory.setGifts("legendary", 25);
    }

    @Test
    public void testDailyOpeningIntegration() {
        // Call the top-level method that should update inventory.
        String result = DailyOpening.generateGift();

        // Sum the total items (both food and gifts) after calling generateGift.
        int totalFood = inventory.getNumberOfFood("common")
                      + inventory.getNumberOfFood("rare")
                      + inventory.getNumberOfFood("legendary");
        int totalGifts = inventory.getNumberOfGifts("common")
                       + inventory.getNumberOfGifts("rare")
                       + inventory.getNumberOfGifts("legendary");
        int newTotal = totalFood + totalGifts;

        // Initial total was 25*3 (for food) + 25*3 (for gifts) = 150.
        int initialTotal = 150;
        
        // We expect the total to increase after generating a gift.
        assertTrue(newTotal > initialTotal, "Inventory should be updated after generateGift() is called");
    }

    
}
