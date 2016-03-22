package com.overstock.android.prototype.interfaces;

import android.net.Uri;

import rx.Observable;

/**
 * Created by itowey on 22/03/16.
 */
public interface OappProvider<T> {
    Observable<T> query(Uri uri);
}
