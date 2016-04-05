package com.overstock.android.prototype.presenter;

import java.util.ArrayList;

import android.content.Context;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.view.CommunitiesView;

/**
 * @author itowey Created on 08/03/16.
 */
public class CommunitiesPresenterImpl implements CommunitiesPresenter {

  private CommunitiesView communitiesView;

  private Context context;

  public CommunitiesPresenterImpl(final Context context) {
    this.context = context;
  }

  @Override
  public void attachedView(final CommunitiesView communitiesView) {
    this.communitiesView = communitiesView;
  }

  @Override
  public void detachView() {
    this.communitiesView = null;
  }

  @Override
  public void populateAndShowCommunities() {

    // TODO: Replace the below block of code with service call. Start of Block
    final ArrayList<Community> communities = new ArrayList<>();

    final String[] imageReferenceArray = context.getResources().getStringArray(R.array.community_image_array);

    final int len = imageReferenceArray.length;
    final int[] imagesResourceArray = new int[len];
    for (int i = 0; i < len; i++) {
      imagesResourceArray[i] = context.getResources().getIdentifier(imageReferenceArray[i], "drawable",
              context.getPackageName());
    }

    final String[] names = context.getResources().getStringArray(R.array.communities_array);

    for (int i = 0; i < imagesResourceArray.length && i < names.length; i++) {

      final Community community = new Community();
      community.setImageId(imagesResourceArray[i]);
      community.setName(names[i]);

      communities.add(community);
    }
    // TODO: End of Block.
    communitiesView.showCommunities(communities);
  }
}
