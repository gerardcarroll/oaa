package com.overstock.android.prototype.component;

import com.overstock.android.prototype.activity.FeedActivity;
import com.overstock.android.prototype.fragment.FeedFragment;

/**
 * Created by itowey on 21/04/16.
 */
public interface FeedActivityInject {
    void inject(FeedActivity feedActivity);
    void inject(FeedFragment feedFragment);

}
