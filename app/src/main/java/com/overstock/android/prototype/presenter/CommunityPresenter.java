package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.CommunityView;

/**
 * @author LeeMeehan Created on 25-Mar-16.
 */
public interface CommunityPresenter {

  void setView(final CommunityView communityView);

  void destroyView();

  void populateAndShowCommunities();
}
