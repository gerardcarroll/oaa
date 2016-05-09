package com.overstock.android.prototype.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Class that handel's collapsing and expanding view elements on the ui.
 *
 * @author LeeMeehan on 05-May-16.
 */
public class ViewAnimationUtils {

  public static void expand(final View view) {
    view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    final int targetHeight = view.getMeasuredHeight();

    view.getLayoutParams().height = 0;
    view.setVisibility(View.VISIBLE);
    Animation animation = new Animation() {
      @Override
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        view.getLayoutParams().height = interpolatedTime == 1
            ? LinearLayout.LayoutParams.WRAP_CONTENT
            : (int) (targetHeight * interpolatedTime);
        view.requestLayout();
      }

      @Override
      public boolean willChangeBounds() {
        return true;
      }
    };

    animation.setDuration((int) (targetHeight / view.getContext().getResources().getDisplayMetrics().density));
    view.startAnimation(animation);
  }

  public static void collapse(final View view) {
    final int initialHeight = view.getMeasuredHeight();

    Animation animation = new Animation() {
      @Override
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (interpolatedTime == 1) {
          view.setVisibility(View.GONE);
        }
        else {
          view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
          view.requestLayout();
        }
      }

      @Override
      public boolean willChangeBounds() {
        return true;
      }
    };

    animation.setDuration((int) (initialHeight / view.getContext().getResources().getDisplayMetrics().density));
    view.startAnimation(animation);
  }
}
