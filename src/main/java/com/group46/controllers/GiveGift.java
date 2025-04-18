package com.group46.controllers;
/**
 * this class is how the users give a pet a gift
 */
public class GiveGift {

    private Inventory inventory; //initialize the variables
    private int happinessIncrease;
    private Score score;

    /**
     * this method represents the constructor
     * @param inventory Inventory
     * @param score Score
     * @param happinessIncrease int
     */
    public GiveGift(Inventory inventory, Score score, int happinessIncrease) {
        this.inventory = inventory;
        this.happinessIncrease = happinessIncrease;
        this.score = score;
    }

}
