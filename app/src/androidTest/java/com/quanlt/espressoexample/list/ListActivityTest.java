package com.quanlt.espressoexample.list;

import android.os.SystemClock;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.quanlt.espressoexample.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by quanlt on 04/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(ListActivity.class);

    @Test
    public void testClickOnItem() throws Exception {
        onData(allOf(is(instanceOf(Contact.class)), withValue("R2-D2")))
                .inAdapterView(withId(R.id.list_contact))
                .perform(click());
        onView(withId(R.id.text_detail_name))
                .check(matches(withText("R2-D2")))
                .check(matches(isDisplayed()));
    }

    public static Matcher<Object> withValue(final String value) {
        return new BoundedMatcher<Object, Contact>(Contact.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has value " + value);
            }

            @Override
            public boolean matchesSafely(Contact item) {
                return item.name.equals(value);
            }
        };
    }
}
