package com.overstock.android.prototype.expresso.component;

import com.overstock.android.prototype.fragment.BrandFragment;
import com.overstock.android.prototype.models.ProductDataService;

/**
 * Created by itowey on 11/03/16.
 */
public interface ApplicationInject {
    void inject(final BrandFragment brandFragment);
    void inject(final ProductDataService productDataService);
}
