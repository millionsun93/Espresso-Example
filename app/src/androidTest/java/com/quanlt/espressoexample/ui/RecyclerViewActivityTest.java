package com.quanlt.espressoexample.ui;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.quanlt.espressoexample.R;
import com.quanlt.espressoexample.RecyclerViewActivity;
import com.quanlt.espressoexample.RecyclerViewActivity.CharacterAdapter.CharacterViewHolder;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by quanlt on 04/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(RecyclerViewActivity.class);

    @Test
    public void testScrollToPositionAndClick() throws Exception {
        onView(ViewMatchers.withId(R.id.rl_numbers))
                .perform(RecyclerViewActions.scrollToPosition(30));
        onView(withText("#30")).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.text_selected_item))
                .check(matches(withText("#30 is selected")));
    }

    @Test
    public void testScrollToSpecificHolder() throws Exception {
        onView(withId(R.id.rl_numbers))
                .perform(RecyclerViewActions.scrollToHolder(withHolderText("20")))
                .check(matches(isDisplayed()));

    }

    private Matcher<RecyclerView.ViewHolder> withHolderText(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder,
                CharacterViewHolder>(CharacterViewHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No viewholder found with text " + text);
            }

            @Override
            protected boolean matchesSafely(CharacterViewHolder item) {
                TextView textView = (TextView) item.itemView.findViewById(android.R.id.text1);
                return textView != null && textView.getText().toString().contains(text);
            }
        };
    }
}
