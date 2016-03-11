package com.overstock.android.prototype.widgets;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * @author LeeMeehan Created on 10-Mar-16.
 */
public class CustomNestedScrollView extends NestedScrollView {
  private Integer slop;

  private Float mInitialMotionX;

  private Float mInitialMotionY;

  public CustomNestedScrollView(Context context) {
    super(context);
    init(context);
  }

  public CustomNestedScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CustomNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    ViewConfiguration config = ViewConfiguration.get(context);
    slop = config.getScaledEdgeSlop();
  }

  private float xDistance, yDistance, lastX, lastY;

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    final float x = ev.getX();
    final float y = ev.getY();
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        xDistance = yDistance = 0f;
        lastX = ev.getX();
        lastY = ev.getY();

        computeScroll();

        break;
      case MotionEvent.ACTION_MOVE:
        final float curX = ev.getX();
        final float curY = ev.getY();
        xDistance += Math.abs(curX - lastX);
        yDistance += Math.abs(curY - lastY);
        lastX = curX;
        lastY = curY;

        if (xDistance > yDistance) {
          return false;
        }
    }

    return super.onInterceptTouchEvent(ev);
  }



}
