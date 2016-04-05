package com.overstock.android.prototype.presenter;

import android.util.Log;

import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.service.CommunityService;
import com.overstock.android.prototype.view.CommunityView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author itowey Created on 08/03/16.
 */
public class CommunityPresenterImpl implements CommunityPresenter {
  private static final String TAG = CommunityPresenterImpl.class.getName();

  private CommunityView communityView;

  private Subscription subscription = Subscriptions.empty();

  private CommunityService communityService;

  @Inject
  public CommunityPresenterImpl(final CommunityService communityService){
    this.communityService = communityService;
  }

  @Override
  public void setView(final CommunityView communityView) {
    this.communityView = communityView;
    if (communityView == null) {
      subscription.unsubscribe();
    }
    else {
      populateAndShowCommunities();
    }
  }

  @Override
  public void destroyView() {
    this.communityView = null;
    subscription.unsubscribe();
  }

  @Override
  public void populateAndShowCommunities() {

    if (communityView.getCommunities() != null) {
      communityView.showCommunities(communityView.getCommunities());
    } else {
      subscription = communityService.getCommunities().subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Community>>() {
                @Override
                public void onCompleted() {
                  Log.d(TAG, "COMPLETED, Finished loading Communities");
                }

                @Override
                public void onError(Throwable e) {
                  Log.d(TAG, "ERROR, Unable to load Communities");
                }

                @Override
                public void onNext(List<Community> communities) {
                  Log.d(TAG, "SUCCESS, Community loaded successfully");
                  communityView.showCommunities(communities);
                }
              });
    }
  }
}
