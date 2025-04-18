package com.group46.components;
/**
 * this class represents a subclass of Pet known as Dog
 */
public class Dog extends pet{
    /**
     * this method represents the constructor
     * @param energy int
     * @param fullness int
     * @param happiness int
     * @param health int
     * @param name String
     */
    public Dog(String name, int health, int fullness, int happiness, int energy) {
        super("dog", name, health, fullness, happiness, energy);
    }

}
