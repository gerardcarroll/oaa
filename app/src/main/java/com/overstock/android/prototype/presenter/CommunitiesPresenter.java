package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.CommunitiesView;

/**
 * @author LeeMeehan Created on 25-Mar-16.
 */
public interface CommunitiesPresenter {

  void attachedView(final CommunitiesView communitiesView);

  void detachView();

  void populateAndShowCommunities();
}
