package com.overstock.android.prototype.activity;

import android.os.Bundle;
import org.parceler.Parcels;
import icepick.Bundler;

/**
 * Class to allow IcePick handle save instance state, while providing custom serialisation and deserialisation of types
 *
 * Created by rconnolly on 4/5/2016.
 */
public class CommunityBundler implements Bundler<Object> {
    @Override
    public void put(String s, Object example, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(example));
    }

    @Override
    public Object get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}