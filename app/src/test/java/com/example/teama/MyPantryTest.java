package com.example.teama;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MyPantryTest extends TestCase {
    ArrayList<String> expected;

    @Before
    public void initialize(){
        expected = new ArrayList<>();
        expected.add("chicken");
        expected.add("ground chicken");
        expected.add("beef");
        expected.add("ground beef");
        expected.add("ham");
        expected.add("pork");
        expected.add("ground pork");
        expected.add("turkey");
        expected.add("ground turkey");
        expected.add("bacon");
        expected.add("sausage");
        expected.add("lamb");
    }

    @Test
    public void testCreateIngredientDB() throws FileNotFoundException {
        ArrayList<String> actual = new ArrayList<>();
        MyPantry dbMethod = new MyPantry();
        dbMethod.createIngredientDB(actual);
        assertEquals(expected, actual);
    }
}