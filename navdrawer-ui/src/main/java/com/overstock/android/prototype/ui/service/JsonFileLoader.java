package com.overstock.android.prototype.ui.service;

import android.content.Context;

import java.io.InputStream;
import java.util.Scanner;

import rx.Observable;
import rx.Subscriber;

/**
 * Util class that provides methods for the async loading of json text files from disk
 *
 * @author itowey
 * @version 1.0
 * @since 06/04/16
 */
public class JsonFileLoader {

    public JsonFileLoader() {

    }

    /**
     * Returns an observable string which contains the contents of the specified resourceId
     *
     * @param context
     * @param resourceId
     * @return
     */
    public Observable<String> getFileAsString(final Context context, final int resourceId) {

        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                InputStream jsonStream = context.getResources().openRawResource(resourceId);
                String jsonString = new Scanner(jsonStream).useDelimiter("\\Z").next();
                subscriber.onNext(jsonString);
            }
        });
    }
}
