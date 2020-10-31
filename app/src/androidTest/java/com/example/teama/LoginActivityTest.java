package com.example.teama;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginActivityTest {
    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> mainActivityActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private String username = "admin";
    private String password = "123";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAdminInputScenario() {
        //input admin into email field
        Espresso.onView(withId(R.id.editTextEmailAddress)).perform(typeText(username));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //click login button
        Espresso.onView(withId(R.id.buttonLogin)).perform(click());
        //checking if it started new intent

    }
    @After
    public void tearDown() throws Exception {
    }
}