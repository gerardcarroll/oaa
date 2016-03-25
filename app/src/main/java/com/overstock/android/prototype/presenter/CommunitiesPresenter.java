package com.overstock.android.prototype.presenter;

import android.content.Context;
import android.content.res.TypedArray;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.view.CommunitiesMvpView;

import java.util.ArrayList;

/**
 * Created by itowey on 08/03/16.
 */
public class CommunitiesPresenter {

  private CommunitiesMvpView communitiesMvpView;

  private Context context;

  public CommunitiesPresenter(final Context context) {
    this.context = context;
  }

  public void attachedView(final CommunitiesMvpView communitiesMvpView) {
    this.communitiesMvpView = communitiesMvpView;
  }

  public void detachView(final CommunitiesMvpView communitiesMvpView) {
    this.communitiesMvpView = null;
  }

  public void populateAndShowCommunities() {

    // start TODO: below block to be replaced by data service call
    final ArrayList<Community> communities = new ArrayList<>();

    final String[] stringArray = context.getResources().getStringArray(R.array.community_image_array);

    final int len = stringArray.length;
    final int[] imagesArray = new int[len];
    for (int i = 0; i < len; i++) {
      imagesArray[i] = context.getResources().getIdentifier(stringArray[i],"drawable",context.getPackageName());
    }

    final String[] names = context.getResources().getStringArray(R.array.communities_array);

    for (int i = 0; i < imagesArray.length && i < names.length; i++) {

      final Community community = new Community();
      community.setImageId(imagesArray[i]);
      community.setName(names[i]);

      communities.add(community);
    }
    // end TODO
    communitiesMvpView.showCommunities(communities);
  }
}
