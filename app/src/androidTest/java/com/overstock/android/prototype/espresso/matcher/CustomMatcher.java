package com.overstock.android.prototype.espresso.matcher;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author LeeMeehan Created on 01-Mar-16.
 */
public class CustomMatcher {

  public static Matcher<Root> isToast() {
    return new TypeSafeMatcher<Root>() {

      @Override
      public void describeTo(Description description) {
        description.appendText("is toast");
      }

      @Override
      public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
          IBinder windowToken = root.getDecorView().getWindowToken();
          IBinder appToken = root.getDecorView().getApplicationWindowToken();
          if (windowToken == appToken) {
            // windowToken == appToken means this window isn't contained by any other windows.
            // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
            return true;
          }
        }
        return false;
      }
    };
  }
}
