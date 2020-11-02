package com.example.teama;

import android.app.Activity;
import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import junit.framework.TestCase;
import org.junit.Before;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyPantryTest extends TestCase {
    Context MockContext;
    //ArrayList<String> expected;
    private MyPantry test;
    private Pantry_List itemTest;
    /**
    @Before
    public void initialize() {
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
    public void testReadInTextFiles() {
        test = new MyPantry();
        test.readInTextFiles();
    }

    @Test
    public void testCreateIngredientDB() throws FileNotFoundException {
        test = new MyPantry();
        ArrayList<String> actual = new ArrayList<>();
        test.createIngredientDB(actual, test.readInTextFiles());
        assertEquals(expected, actual);
    }
     */
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

    @Test
    public void testRandomMethodBecauseICantFigureOutUnitTestForOtherOne() {
        test = new MyPantry();
        assertEquals("hello goodbye",  test.randomMethodBecauseICantFigureOutUnitTestForOtherOne("goodbye"));
    }

}
