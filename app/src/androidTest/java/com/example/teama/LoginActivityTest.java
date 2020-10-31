package com.example.teama;

import android.content.ComponentName;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
        //input password into password field
        Espresso.onView(withId(R.id.editTextRegPW)).perform(typeText(password));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //click login button
        Espresso.onView(withId(R.id.buttonLogin)).perform(click());
        //checking if it started new intent
        intended(hasComponent(new ComponentName(getApplicationContext(), MainActivity.class)));

    }
    @After
    public void tearDown() throws Exception {
    }
}