package com.overstock.android.prototype.espresso.viewaction;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by itowey on 20/04/16.
 */
public final class HorizontialScrollFragmentActionOnItemAtPositionViewAction implements
        ViewAction {

    private final int position;
    private final ViewAction viewAction;

    private HorizontialScrollFragmentActionOnItemAtPositionViewAction(int position, ViewAction viewAction) {
        this.position = position;
        this.viewAction = viewAction;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Matcher<View> getConstraints() {
        return allOf(isAssignableFrom(RecyclerView.class), isEnabled());
    }

    @Override
    public String getDescription() {
        return "actionOnItemAtPosition performing ViewAction: " + viewAction.getDescription()
                + " on item at position: " + position;
    }

    @Override
    public void perform(UiController uiController, View view) {
        RecyclerView recyclerView = (RecyclerView) view;

        HorizontialScrollFragmentScrollToPositionViewAction.scrollToPosition(position).perform(uiController, view);
        uiController.loopMainThreadUntilIdle();

        @SuppressWarnings("unchecked")
        RecyclerView.ViewHolder viewHolderForPosition =  recyclerView.findViewHolderForPosition(position);
        if (null == viewHolderForPosition) {
            throw new PerformException.Builder().withActionDescription(this.toString())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new IllegalStateException("No view holder at position: " + position))
                    .build();
        }

        View viewAtPosition = viewHolderForPosition.itemView;
        if (null == viewAtPosition) {
            throw new PerformException.Builder().withActionDescription(this.toString())
                    .withViewDescription(HumanReadables.describe(viewAtPosition))
                    .withCause(new IllegalStateException("No view at position: " + position)).build();
        }

        viewAction.perform(uiController, viewAtPosition);
    }


    public static ViewAction actionOnItemAtPosition(final int position,
                                                                            final ViewAction viewAction) {
        return new HorizontialScrollFragmentActionOnItemAtPositionViewAction(position, viewAction);
    }
}