package com.overstock.android.prototype.animatorutils;

import android.view.View;

import com.daimajia.slider.library.Animations.BaseAnimationInterface;

/**
 * This class is overriding the default behaviour of the description.
 *
 * @author LeeMeehan.
 * @since Created on 20-Apr-16.
 */
public class CustomDescriptionAnimation implements BaseAnimationInterface {
  @Override
  public void onPrepareCurrentItemLeaveScreen(View current) {
    hideDescription(current);
  }

  @Override
  public void onPrepareNextItemShowInScreen(View next) {
    hideDescription(next);
  }

  @Override
  public void onCurrentItemDisappear(View view) {
    hideDescription(view);
  }

  @Override
  public void onNextItemAppear(View view) {
    hideDescription(view);
  }

  private void hideDescription(final View view) {
    View descriptionLayout = view.findViewById(com.daimajia.slider.library.R.id.description_layout);
    if (descriptionLayout != null) {
      view.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);
    }
  }
}
