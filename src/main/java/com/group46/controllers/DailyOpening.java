package com.group46.controllers;

import java.util.Random;
/**
 * this class is how we generate the gifts for the daily opening feature of our game
 */
public class DailyOpening {

    private static Random gift = new Random(); //initialize the variables
    private static Inventory inventory = Inventory.getInventory();


    /**
     * This method generates a random prize out of six options and adds it
     * to the players inventory
     * @return prizeWon
     */
    public static String generateGift() {
        int random = gift.nextInt(6);//using Java's .nextInt() function to generate a number between and 5
        String prizeWon = "";
        int amount = 0;
        switch(random){ //using switch to cycle through the possible random values
            case 0:
                prizeWon = "common food items"; //and depending on that value assign it the appropriate prize, either a gift or food and their rarity, along with amount, and add them to the appropriate inventory slot.
                amount = 10;
                inventory.setFood("common", inventory.getNumberOfFood("common") + amount);
                break;
            case 1:
                prizeWon = "rare food items";
                amount = 7;
                inventory.setFood("rare", inventory.getNumberOfFood("rare") + amount);
                break;
            case 2:
                prizeWon = "legendary food items";
                amount = 4;
                inventory.setFood("legendary", inventory.getNumberOfFood("legendary") + amount);
                break;
            case 3:
                prizeWon = "common gift items";
                amount = 10;
                inventory.setGifts("common", inventory.getNumberOfGifts("common") + amount);
                break;
            case 4:
                prizeWon = "rare gift items";
                amount = 7;
                inventory.setGifts("rare", inventory.getNumberOfGifts("rare") + amount);
                break;
            case 5:
                prizeWon = "legendary gift items";
                amount = 4;
                inventory.setGifts("legendary", inventory.getNumberOfGifts("legendary") + amount);
                break;
        }
        return "You won " + amount + " " + prizeWon; //return a String saying what you won
    }
}
