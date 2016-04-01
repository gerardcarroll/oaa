package com.overstock.android.prototype.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.client.FeedClient;
import com.overstock.android.prototype.model.Feed;
import com.overstock.android.prototype.presenter.FeedPresenter;
import com.overstock.android.prototype.view.FeedView;

/**
 * @author LeeMeehan Created on 31-Mar-16.
 */
public class FeedPresenterImpl implements FeedPresenter {
  private static final String TAG = FeedPresenterImpl.class.getName();

  private Subscription subscription = Subscriptions.empty();

  private FeedView feedView;

  private Context context;

  @Override
  public void setView(final FeedView feedView, final Context context) {
    this.feedView = feedView;
    this.context = context;
    if (feedView == null) {
      subscription.unsubscribe();
    }
    else {
      refreshFeed();
    }
  }

  @Override
  public void onDestroy() {
    feedView = null;
    context = null;
    subscription.unsubscribe();
  }

  @Override
  public void refreshFeed() {

    subscription = FeedClient.getClient(context).getFeed().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Feed>>() {
          @Override
          public void onCompleted() {
            Log.d(TAG, "FeedService.GetFeed has no more data to emit.");
          }

          @Override
          public void onError(Throwable e) {
            Log.e(TAG, "Error on subscribing to FeedService.GetFeed");
          }

          @Override
          public void onNext(List<Feed> feed) {
            Log.d(TAG, "Next value on subscribing to FeedService.GetFeed");
            feedView.updateFeed((ArrayList<Feed>) feed);
          }
        });
  }
}
