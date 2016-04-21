package com.overstock.android.prototype.component;

import android.content.Context;

import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.module.FeedActivityModule;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Component;

/**
 * Created by itowey on 11/03/16.
 */
@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {FeedActivityModule.class}
)
public interface FeedActivityComponent extends FeedActivityInject {

    final class Initializer {
        public static FeedActivityComponent init(Context context) {
            return DaggerFeedActivityComponent.builder()
                    .applicationComponent(OAppPrototypeApplication.get(context).getComponent())
                    .build();
        }
    }
}
