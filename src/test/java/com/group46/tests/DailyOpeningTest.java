package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.group46.controllers.DailyOpening;
import com.group46.controllers.Inventory;

/**
 * This test class verifies the functionality of the DailyOpening.generateGift() method.
 * It ensures that when a gift is generated, the appropriate inventory count is updated.
 */
public class DailyOpeningTest {
    // A reference to the Inventory instance used in the game.
    private Inventory inventory;

    /**
     * This method is executed before each test case.
     * It resets the inventory counts to known values to ensure consistent test results.
     */
    @BeforeEach
    public void setup() {
        // Retrieve the singleton Inventory instance.
        inventory = Inventory.getInventory();
        // Set the initial counts for each type of food.
        inventory.setFood("common", 25);
        inventory.setFood("rare", 25);
        inventory.setFood("legendary", 25);
        // Set the initial counts for each type of gift.
        inventory.setGifts("common", 25);
        inventory.setGifts("rare", 25);
        inventory.setGifts("legendary", 25);
    }

    /**
     * This test method calls the generateGift() method and then verifies:
     * - That the output message starts with "You won"
     * - The corresponding inventory counter is correctly incremented based on the type of gift/food awarded.
     */
    @Test
    public void testGenerateGift() {
        // Save the current inventory counts for food and gifts before generating a gift.
        int initialCommonFood = inventory.getNumberOfFood("common");
        int initialRareFood = inventory.getNumberOfFood("rare");
        int initialLegendaryFood = inventory.getNumberOfFood("legendary");
        int initialCommonGift = inventory.getNumberOfGifts("common");
        int initialRareGift = inventory.getNumberOfGifts("rare");
        int initialLegendaryGift = inventory.getNumberOfGifts("legendary");

        // Call the generateGift() method which randomly selects a gift and updates the inventory.
        String result = DailyOpening.generateGift();
        
        // Verify that the returned string starts with "You won"
        assertTrue(result.startsWith("You won"), "Result should start with 'You won'");

        // Check which type of gift or food item was awarded, then assert that the inventory
        // has been updated correctly according to the expected increment.
        if(result.contains("common food items")) {
            // For common food items, the expected increase is 10.
            assertEquals(initialCommonFood + 10, inventory.getNumberOfFood("common"),
                "Common food items should increase by 10");
        } else if(result.contains("rare food items")) {
            // For rare food items, the expected increase is 7.
            assertEquals(initialRareFood + 7, inventory.getNumberOfFood("rare"),
                "Rare food items should increase by 7");
        } else if(result.contains("legendary food items")) {
            // For legendary food items, the expected increase is 4.
            assertEquals(initialLegendaryFood + 4, inventory.getNumberOfFood("legendary"),
                "Legendary food items should increase by 4");
        } else if(result.contains("common gift items")) {
            // For common gift items, the expected increase is 10.
            assertEquals(initialCommonGift + 10, inventory.getNumberOfGifts("common"),
                "Common gift items should increase by 10");
        } else if(result.contains("rare gift items")) {
            // For rare gift items, the expected increase is 7.
            assertEquals(initialRareGift + 7, inventory.getNumberOfGifts("rare"),
                "Rare gift items should increase by 7");
        } else if(result.contains("legendary gift items")) {
            // For legendary gift items, the expected increase is 4.
            assertEquals(initialLegendaryGift + 4, inventory.getNumberOfGifts("legendary"),
                "Legendary gift items should increase by 4");
        } else {
            // If the result doesn't match any expected type, then the test fails.
            fail("Generated gift result did not match any expected type");
        }
    }
}
