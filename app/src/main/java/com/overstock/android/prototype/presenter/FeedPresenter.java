package com.overstock.android.prototype.presenter;

import android.content.Context;

import com.overstock.android.prototype.view.FeedView;

/**
 * @author LeeMeehan Created on 31-Mar-16.
 */
public interface FeedPresenter {

  void setView(final FeedView feedView, final Context context);

  void refreshFeed();

  void onDestroy();
}
