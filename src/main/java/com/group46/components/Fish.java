package com.group46.components;
/**
 * this class represents a subclass of the Pet class known as Fish
 */
public class Fish extends pet{
    /**
     * this method represents the constructor
     * @param energy int
     * @param fullness int
     * @param happiness int
     * @param health int
     * @param name String
     */
    public Fish(String name, int health, int fullness, int happiness, int energy) {
        super("fish", name, health, fullness, happiness, energy);
    }

}
