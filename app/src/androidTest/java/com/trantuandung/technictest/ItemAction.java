package com.trantuandung.technictest;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.click;

public class ItemAction {
    public ViewAction clickChildViewWithId(final int id) {
	return new ViewAction() {
	    @Override
	    public Matcher<View> getConstraints() {
		return click().getConstraints();
	    }

	    @Override
	    public String getDescription() {
		return "Click on a child view with specified id.";
	    }

	    @Override
	    public void perform(UiController uiController, View view) {

		View v = view.findViewById(id);
		if (v != null) {
		    v.performClick();
		}
	    }
	};
    }
}
