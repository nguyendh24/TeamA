package com.example.teama;

import org.junit.Test;
import junit.framework.TestCase;

public class PantryActivityTest extends TestCase {
    private PantryActivity test;
    private Pantry_List itemTest;

    @Test
    public void testToString_true() {
        itemTest = new Pantry_List("apple", 2);
        assertEquals(String.format("%-25s %s%d", "apple","x ", 2), itemTest.toString());
    }

    @Test
    public void testToString_false() {
        itemTest = new Pantry_List("apple", 2);
        assertNotSame(String.format("%-25s %s%d", "orange","x ", 2), itemTest.toString());
    }
    
}
