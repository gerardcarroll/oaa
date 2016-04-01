package com.overstock.android.prototype.component;

import android.app.Application;

import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.adapters.FeedAdapter;
import com.overstock.android.prototype.adapters.ProductAdapter;
import com.overstock.android.prototype.fragment.BrandFragment;
import com.overstock.android.prototype.fragment.FeedFragment;
import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
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

  void inject(final ProductDetailActivity productDetailActivity);

  void inject(final FeedFragment feedFragment);

  void inject(final ProductAdapter productAdapter);

  void inject(final FeedAdapter feedAdapter);
}
