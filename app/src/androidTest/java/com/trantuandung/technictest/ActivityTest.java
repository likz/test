package com.trantuandung.technictest;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;

import com.squareup.spoon.Spoon;
import com.trantuandung.technictest.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityTest {
    protected final int WAIT = 200; //200 ms

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSwitchCartAndShopping() throws Exception {
        MainActivity activity = mActivityRule.getActivity();
        String fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);
    }

    @Test
    public void testAddBookToCart() throws Exception {
        MainActivity activity = mActivityRule.getActivity();
        String fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        testClickOnAddOeDeleteBook(activity,true);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);
    }


    @Test
    public void testDeleteBookFromCart() throws Exception {
        MainActivity activity = mActivityRule.getActivity();
        String fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);

        onView(withId(R.id.mainToolbarCart)).check(matches(isDisplayed())).perform(click());
        fileName = "Pannier_screen";
        Spoon.screenshot(activity, fileName);

        testClickOnAddOeDeleteBook(activity,false);

        onView(withId(R.id.mainToolbarContinueShopping)).check(matches(isDisplayed())).perform(click());
        fileName = "Initial_screen";
        Spoon.screenshot(activity, fileName);
    }

    @Test
    public void testScrollOnList() throws InterruptedException {
        MainActivity activity = mActivityRule.getActivity();

        onView(withId(R.id.mainContent)).check(matches(isDisplayed()));
        int position;
        onView(withId(R.id.mainContent)).perform(RecyclerViewActions.scrollToPosition(position = 6));
        Thread.sleep(WAIT);
        com.squareup.spoon.Spoon.screenshot(activity, "test_scroll_on_list_position_"+ position);

        onView(withId(R.id.mainContent)).perform(RecyclerViewActions.scrollToPosition(position = 0));
        Thread.sleep(WAIT);
        com.squareup.spoon.Spoon.screenshot(activity, "test_scroll_on_list_position_"+ position);

        onView(withId(R.id.mainContent)).perform(RecyclerViewActions.scrollToPosition(position = 2));
        Thread.sleep(WAIT);
        com.squareup.spoon.Spoon.screenshot(activity, "test_scroll_on_list_position_"+ position);

        onView(withId(R.id.mainContent)).perform(RecyclerViewActions.scrollToPosition(position = 5));
        Thread.sleep(WAIT);
        com.squareup.spoon.Spoon.screenshot(activity, "test_scroll_on_list_position_" + position);
    }

    protected void testClickOnAddOeDeleteBook(MainActivity activity, boolean isAdd) throws InterruptedException {
        onView(withId(R.id.mainContent)).check(matches(isDisplayed()));
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.mainContent);
        assertNotNull(recyclerView);
        int size = recyclerView.getChildCount();

        int position;
        for(int i= 0; i < size; i++) {
            if(i < 4) {
                onView(
                        withId(R.id.mainContent)).perform(
                        RecyclerViewActions.actionOnItemAtPosition(
                                position = i,
                                new ItemAction().clickChildViewWithId(
                                        isAdd ? R.id.bookItem_add : R.id.bookItem_delete)));
                Thread.sleep(WAIT);
                Spoon.screenshot(activity, "click_on_add_or_delete_book_position_" + position);
            }else{
                break;
            }
        }
    }
}
