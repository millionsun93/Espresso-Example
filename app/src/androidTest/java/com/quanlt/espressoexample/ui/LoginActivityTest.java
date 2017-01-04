package com.quanlt.espressoexample.ui;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;

import com.quanlt.espressoexample.LoadingProgressIdlingResource;
import com.quanlt.espressoexample.R;
import com.quanlt.espressoexample.idling.LoadingProgress;
import com.quanlt.espressoexample.idling.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by quanlt on 04/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void testLoginFail() throws Exception {
        LoadingProgressIdlingResource idlingResource = new LoadingProgressIdlingResource(
                ((FragmentActivity)mActivityTestRule.getActivity()).getSupportFragmentManager()
                , LoadingProgress.TAG);
        Espresso.registerIdlingResources(idlingResource);
        onView(ViewMatchers.withId(R.id.edit_username)).perform(typeText("rightuser"));
        onView(withId(R.id.edit_password)).perform(typeText("123456"));
        onView(withId(R.id.edit_password_verify)).perform(typeText("123456"));
        onView(withId(R.id.edit_email)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.button_submit)).perform(click());
        onView(withId(R.id.text_result)).check(matches(withText("Success")));
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
