package com.example.teama;

import org.junit.Test;
import junit.framework.TestCase;

public class PantryActivityTest extends TestCase {
    private PantryActivity test;
    private Pantry_List itemTest;

    @Test
    public void testToString_true() {
        itemTest = new Pantry_List("apple", 2);
        assertEquals("apple", itemTest.toString());

    }

    @Test
    public void testToString_false() {
        itemTest = new Pantry_List("apple", 2);
        assertNotSame("orange", itemTest.toString());
    }
    
}
