package com.overstock.android.prototype.service;

import android.content.Context;

import com.overstock.android.prototype.R;
import com.parse.Parse;

/**
 * Created by itowey on 21/04/16.
 */
public class ParseService {

    public ParseService(Context context) {
        //Parse init
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId(context.getString(R.string.parse_application_id))
                .server(context.getString(R.string.parse_service_url))
                .build());
    }
}
