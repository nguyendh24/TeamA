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
    private MyPantry test;
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
