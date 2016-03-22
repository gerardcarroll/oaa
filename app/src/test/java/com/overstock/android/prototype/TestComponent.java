package com.overstock.android.prototype;

import com.overstock.android.prototype.component.HomeActivityInject;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.module.HomeActivityFragmentsModule;

import dagger.Component;

@Component(modules = HomeActivityFragmentsModule.class)
public interface TestComponent extends HomeActivityInject {
    @Override
    void inject(HomeFragment homeFragment);
}
