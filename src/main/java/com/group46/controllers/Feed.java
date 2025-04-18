package com.group46.controllers;
/**
 * this class is how the users feed a pet
 */
public class Feed {
    private Inventory inventory; //initialize the variables
    private Score score;

    /**
     * this method represents the constructor of the class
     * @param score Score
     * @param inventory Inventory
     */
    public Feed(Inventory inventory, Score score) {
        this.inventory = inventory;
        this.score = score;
    }

}
