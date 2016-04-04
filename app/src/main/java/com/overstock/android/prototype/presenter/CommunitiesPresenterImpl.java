package com.overstock.android.prototype.presenter;

import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.interfaces.CommunityClient;
import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.view.CommunitiesView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author itowey Created on 08/03/16.
 */
public class CommunitiesPresenterImpl implements CommunitiesPresenter {
  private static final String TAG = CommunitiesPresenterImpl.class.getName();

  private CommunitiesView communitiesView;

  private Context context;

  private Subscription subscription = Subscriptions.empty();

  public CommunitiesPresenterImpl(final Context context) {
    this.context = context;
  }

  @Override
  public void setView(final CommunitiesView communitiesView, final Context context) {
    this.communitiesView = communitiesView;
    this.context = context;
    if (communitiesView == null){
      subscription.unsubscribe();
    } else{
      populateAndShowCommunities();
    }
  }

    @Override
    public void destroyView() {
    this.communitiesView = null;
    this.context = null;
    subscription.unsubscribe();
  }

  @Override
  public void populateAndShowCommunities() {

    subscription = CommunityClient.getClient(context).getCommunities().subscribeOn(Schedulers.io())
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
                Log.d(TAG, "SUCCESS, Successfully loaded Communities");
                communitiesView.showCommunities(communities);
              }
            });

//    // TODO: Replace the below block of code with service call. Start of Block
//    final ArrayList<Community> communities = new ArrayList<>();
//
//    final String[] imageReferenceArray = context.getResources().getStringArray(R.array.community_image_array);
//
//    final int len = imageReferenceArray.length;
//    final int[] imagesResourceArray = new int[len];
//    for (int i = 0; i < len; i++) {
//      imagesResourceArray[i] = context.getResources().getIdentifier(imageReferenceArray[i], "drawable",
//              context.getPackageName());
//    }
//
//    final String[] names = context.getResources().getStringArray(R.array.communities_array);
//
//    for (int i = 0; i < imagesResourceArray.length && i < names.length; i++) {
//
//      final Community community = new Community();
//      community.setImageId(imagesResourceArray[i]);
//      community.setName(names[i]);
//
//      communities.add(community);
//    }
//    // TODO: End of Block.
//    communitiesView.showCommunities(communities);
  }
}
