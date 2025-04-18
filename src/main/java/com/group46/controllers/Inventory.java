package com.group46.controllers;

import java.util.Arrays;
/**
 * this class represents the inventory in our game, which allows users to access all their food and gifts
 */
public class Inventory {

  //intializing the variables
  private String[] foodItems;
  private String[] giftItems;
  private int numberOfFood;


  //new vars
  private int commonFoodItems;
  private int rareFoodItems;
  private int legendaryFoodItems;
  private int commonGiftItems;
  private int rareGiftItems;
  private int legendaryGiftItems;
  private int numberOfGifts = 0;
  public static Inventory inventory;


  /**
   * constructor method to set default inventory values
   */
  public Inventory() {
    this.commonFoodItems = 25;
    this.rareFoodItems = 25;
    this.legendaryFoodItems = 25;
    this.commonGiftItems = 25;
    this.rareGiftItems = 25;
    this.legendaryGiftItems = 25;
  }

  /**
   * this method creates one public instance of inventory
   * @return inventory
   */
  public static Inventory getInventory() {
    if (inventory == null) {
      inventory = new Inventory();
    }
    return inventory;
  }

  /**
   * Decrements the count of whichever food item is being used
   *
   * @param foodItem
   */
  public void useFood(String foodItem) {
    switch (foodItem) {
      case "common":
        this.commonFoodItems -= 1;
        break;
      case "rare":
        this.rareFoodItems -= 1;
        break;
      case "legendary":
        this.legendaryFoodItems -= 1;
        break;
    }
  }

  /**
   * Decrements the count of whichever gift item is being used
   *
   * @param giftItem
   */
  public void useGift(String giftItem) {
    switch (giftItem) {
      case "common":
        if (this.commonGiftItems > 0) this.commonGiftItems--;
        break;
      case "rare":
        if (this.rareGiftItems > 0) this.rareGiftItems--;
        break;
      case "legendary":
        if (this.legendaryGiftItems > 0) this.legendaryGiftItems--;
        break;
    }
  }

  /**
   * This method increments the counter of a given gift rarity
   *
   * @param giftItem
   */
  public void addGift(String giftItem) {
    switch (giftItem) {
      case "common":
        this.commonGiftItems++;
      case "rare":
        this.rareGiftItems++;
      case "legendary":
        this.legendaryGiftItems++;
    }
  }


  /**
   * sets a specific gift rarity count to a given value
   * @param giftItem
   * @param num
   */
  public void setGifts(String giftItem, int num) {
    switch (giftItem) {
      case "common":
        this.commonGiftItems = num;
        break;
      case "rare":
        this.rareGiftItems = num;
        break;
      case "legendary":
        this.legendaryGiftItems = num;
        break;
    }
  }

  /**
   * This method increments the counter of a given food rarity
   *
   * @param foodItem
   */
  public void addFood(String foodItem) {
    switch (foodItem) {
      case "common":
        this.commonFoodItems++;
        break;
      case "rare":
        this.rareFoodItems++;
        break;
      case "legendary":
        this.legendaryFoodItems++;
        break;
    }
  }

  /**
   * sets a specific food rarity count to a given value
   * @param foodItem
   * @param num
   */
  public void setFood(String foodItem, int num) {
    switch (foodItem) {
      case "common":
        this.commonFoodItems = num;
        break;
      case "rare":
        this.rareFoodItems = num;
        break;
      case "legendary":
        this.legendaryFoodItems = num;
        break;
    }
  }


  /**
   * This method returns the amount of a given food type
   *
   * @param foodItem
   * @return number of food items
   */
  public int getNumberOfFood(String foodItem) {
    switch (foodItem) {
      case "common":
        return this.commonFoodItems;
      case "rare":
        return this.rareFoodItems;
      case "legendary":
        return this.legendaryFoodItems;
    }
    return 0;
  }

  /**
   * This method returns the amount of a given gift type
   *
   * @param giftItem
   * @return number of gift items
   */
  public int getNumberOfGifts(String giftItem) {
    switch (giftItem) {
      case "common":
        return this.commonGiftItems;
      case "rare":
        return this.rareGiftItems;
      case "legendary":
        return this.legendaryGiftItems;
    }
    return 0;
  }


  /**
   * this method makes it possible to remove an item from the inventory when it is used
   * @param item String
   */
  public void removeFromInventory(String item) {
    for (int i = 0; i < foodItems.length; i++) { //first cycling through all the food items
      if (foodItems[i].equals(item)) { //look for the one you're looking to remove
        foodItems[i] = foodItems[numberOfFood - 1]; //remove the item
        foodItems[numberOfFood - 1] = null;//set that value to null
        numberOfFood--; //make the number of food go down by one and return
        return;
      }
    }

    for (int i = 0; i < giftItems.length; i++) { //exactly the same as the one above just for the gifts
      if (giftItems[i].equals(item)) {
        giftItems[i] = giftItems[numberOfGifts - 1];
        giftItems[numberOfGifts - 1] = null;
        numberOfGifts--;
        return;
      }
    }
  }

  /**
   * this method returns the food array
   * @return foodItems
   */
  public String getFoodItems() {
    System.out.println(Arrays.toString(foodItems)); //first print the array as a string using toString
    return Arrays.toString(foodItems); //then return the array
  }
  /**
   * this method returns the gift array
   * @return foodItems
   */
  public String getGiftItems() {
    System.out.println(Arrays.toString(giftItems)); //exactly the same as the one above, just with the gift array
    return Arrays.toString(giftItems);
  }
}
