package com.overstock.android.prototype.component;

import android.app.Application;

import com.overstock.android.prototype.activity.CommunityActivity;
import com.overstock.android.prototype.adapters.FeedAdapter;
import com.overstock.android.prototype.adapters.ProductAdapter;
import com.overstock.android.prototype.fragment.BrandFragment;
import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.fragment.ImageGalleryFragment;
import com.overstock.android.prototype.fragment.MoreInformationFragment;
import com.overstock.android.prototype.fragment.ProductBottomSheetFragment;
import com.overstock.android.prototype.fragment.ProductDetailsFragment;
import com.overstock.android.prototype.fragment.SignInWithEmailFragment;
import com.overstock.android.prototype.fragment.SignUpWithEmailFragment;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.service.OappGoogleAuthService;

/**
 * @author itowey Created on 11/03/16.
 */
public interface ApplicationInject {

  Application application();

  void inject(final BrandFragment brandFragment);

  void inject(final ProductDataService productDataService);

  void inject(final OappGoogleAuthService oappGoogleAuthService);

  void inject(final GoogleFederatedIdentityFragment googleFederatedIdentityFragment);

  void inject(final ProductDetailsFragment productDetailsFragment);

  void inject(final ProductAdapter productAdapter);

  void inject(final FeedAdapter feedAdapter);

  void inject(final CommunityActivity communitiesActivity);

  void inject(final ProductBottomSheetFragment productBottomSheetFragment);

  void inject(final SignInWithEmailFragment signInWithEmailFragment);

  void inject(final SignUpWithEmailFragment signUpWithEmailFragment);

  void inject(final ImageGalleryFragment imageGalleryFragment);

  void inject(final MoreInformationFragment moreInformationFragment);

}
