package com.overstock.android.prototype.client;

import android.content.Context;

import com.overstock.android.prototype.service.FeedService;
import com.overstock.android.prototype.service.ParseService;

/**
 * Created by gcarroll on 21/04/2016.
 */
public class ParseClient {

    private Context context;

    public ParseClient(final Context context) {
        this.context = context;
    }

    public ParseService getClient() {
    return  new ParseService() {
    };
    }
}
