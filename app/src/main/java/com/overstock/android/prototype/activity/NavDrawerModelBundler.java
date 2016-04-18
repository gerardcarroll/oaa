package com.overstock.android.prototype.activity;

import android.os.Bundle;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * Created by itowey on 06/04/16.
 */
public class NavDrawerModelBundler implements Bundler<Object> {

    @Override
    public void put(String s, Object example, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(example));
    }

    @Override
    public Object get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
