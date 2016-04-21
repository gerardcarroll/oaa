package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.client.FeedClient;
import com.overstock.android.prototype.module.scope.ActivityScope;
import com.overstock.android.prototype.presenter.FeedActivityPresenter;
import com.overstock.android.prototype.presenter.FeedPresenter;
import com.overstock.android.prototype.presenter.impl.FeedActivityPresenterImpl;
import com.overstock.android.prototype.presenter.impl.FeedPresenterImpl;
import com.overstock.android.prototype.service.FeedService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itowey on 11/03/16.
 */
@Module
public class FeedActivityModule {

  @Provides
  @ActivityScope
  public FeedActivityPresenter providesFeedActivityPresenter() {
    return new FeedActivityPresenterImpl();
  }

  @Provides
  @ActivityScope
  public FeedClient providesFeedClient(final Application application) {
    return new FeedClient(application);
  }

  @Provides
  @ActivityScope
  public FeedService providesFeedService(final FeedClient feedClient) {
    return feedClient.getClient();
  }

  @Provides
  @ActivityScope
  public FeedPresenter providesFeedPresenter(final FeedService feedService) {
    return new FeedPresenterImpl(feedService);
  }



}
