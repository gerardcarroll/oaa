package com.overstock.android.prototype.presenter;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

import com.overstock.android.prototype.view.BrandView;

/**
 * @author LeeMeehan Created on 07-Mar-16.
 */
public class BrandPresenterImpl implements BrandPresenter {
  private Subscription subscription = Subscriptions.empty();

  private BrandView brandView;

  @Override
  public void setView(final BrandView brandView) {
    this.brandView = brandView;
      refresh();
  }

  @Override
  public void onDestroy() {
    brandView = null;
    subscription.unsubscribe();
  }

  public void refresh() {
    // implement logic to display lists
  }

}
