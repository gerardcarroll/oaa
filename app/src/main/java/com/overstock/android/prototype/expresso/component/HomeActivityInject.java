package com.overstock.android.prototype.expresso.component;

import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.fragment.HomeFragment;

/**
 * Created by itowey on 11/03/16.
 */
public interface HomeActivityInject {
    void inject(HomeActivity homeActivity);
    void inject(HomeFragment homeFragment);
}
