package com.overstock.android.prototype.espresso.matcher;

import android.view.View;

import com.daimajia.slider.library.SliderLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by itowey on 26/04/16.
 */
public class SliderMatcher {
    public static Matcher<View> withCurrentPositiom (final int position) {
        return new TypeSafeMatcher<View>() {
            @Override public boolean matchesSafely (final View view) {
                int tmpPosition = 0;
                try{
                    tmpPosition = ((SliderLayout) view).getCurrentPosition();
                } catch(Exception e){

                }
                return tmpPosition == position;
            }

            @Override public void describeTo (final Description description) {
                description.appendText ("SliderLayout should be at position " + position);
            }
        };
    }
}
