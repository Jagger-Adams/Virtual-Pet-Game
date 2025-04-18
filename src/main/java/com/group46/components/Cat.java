package com.group46.components;

/**
 * this class creates a subclass of the Pet class known as cat
 */
public class Cat extends pet{
    /**
     * this method represents the constructor
     * @param energy int
     * @param fullness int
     * @param happiness int
     * @param health int
     * @param name String
     */
    public Cat(String name, int health, int fullness, int happiness, int energy) {
        super("cat", name, health, fullness, happiness, energy);
    }
}
