package com.overstock.android.prototype.espresso.viewaction;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by itowey on 20/04/16.
 */

public final class HorizontialScrollFragmentScrollToPositionViewAction implements ViewAction {
    private final int position;

    private HorizontialScrollFragmentScrollToPositionViewAction(int position) {
        this.position = position;
    }

    public static <VH extends RecyclerView.ViewHolder> ViewAction scrollToPosition(final int position) {
        return new HorizontialScrollFragmentScrollToPositionViewAction(position);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Matcher<View> getConstraints() {
        return allOf(isAssignableFrom(RecyclerView.class), isEnabled());
    }

    @Override
    public String getDescription() {
        return "scroll RecyclerView to position: " + position;
    }

    @Override
    public void perform(UiController uiController, View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.scrollToPosition(position);
    }
}