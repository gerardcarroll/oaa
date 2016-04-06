package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.FeedView;

/**
 * @author LeeMeehan Created on 31-Mar-16.
 */
public interface FeedPresenter {

  void setView(final FeedView feedView);

  void refreshFeed();

  void onDestroy();
}
