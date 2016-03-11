package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.BrandView;

/**
 * @author LeeMeehan Created on 07-Mar-16.
 */
public interface BrandPresenter {
  void setView(final BrandView brandView);

  void onDestroy();
}
