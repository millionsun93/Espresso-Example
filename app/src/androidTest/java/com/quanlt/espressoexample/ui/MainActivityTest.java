package com.quanlt.espressoexample.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.quanlt.espressoexample.MainActivity;
import com.quanlt.espressoexample.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.*;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by quanlt on 04/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public IntentsTestRule mActivityTestRule = new IntentsTestRule(MainActivity.class);


    @Test
    public void testToolbar() throws Exception {
        onView(allOf(isAssignableFrom(TextView.class),
                isDescendantOfA(isAssignableFrom(Toolbar.class))))
                .check(matches(isDisplayed()));
        onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(startsWith("Espresso"))));

    }

    @Test
    public void testClickOnLoginWillGoToLoginScreen() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Login")).perform(click());
        intended(toPackage("com.quanlt.espressoexample"));
        onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(is("Login"))));
    }

    @Test
    public void testLoginSuccessWillShowWelcome() throws Exception {
        Intent resultIntent = new Intent();
        String username = "ethan";
        resultIntent.putExtra(MainActivity.EXTRA_USERNAME, username);
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, resultIntent);
        intending(toPackage("com.quanlt.espressoexample")).respondWith(result);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.text_username)).check(matches(withText(username)));

    }

    private Matcher<Object> withToolbarTitle(final Matcher<String> text) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                text.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(Toolbar toolbar) {
                return text.matches(toolbar.getTitle());
            }
        };
    }
}
