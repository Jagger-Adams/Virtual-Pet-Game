package com.group46.tests;

import com.group46.components.Cat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CatTest {
    @Test
    void testCatConstructor() {
        Cat cat = new Cat("Goku", 100, 10, 94, 91);

        assertEquals("Goku", cat.getName(), "Cat's name should be 'Goku'");
        assertEquals(100, cat.getHealth(), "Cat's health should be 100");
        assertEquals(10, cat.getFullness(), "Cat's fullness should be 10");
        assertEquals(94, cat.getHappiness(), "Cat's happiness should be 94");
        assertEquals(91, cat.getEnergy(), "Cat's energy should be 91");
    }
}
