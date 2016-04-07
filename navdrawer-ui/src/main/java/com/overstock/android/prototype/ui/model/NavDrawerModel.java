package com.overstock.android.prototype.ui.model;

import android.content.Context;

import com.google.gson.Gson;
import com.overstock.android.prototype.ui.R;
import com.overstock.android.prototype.ui.service.JsonFileLoader;

import org.parceler.Transient;

import java.util.Arrays;
import java.util.List;

/**
 * Model object that manages retrieval of nav drawer item from observable call to json file.
 * Non transient atteributes State also persisted with icepick
 *
 * @author itowey
 * @version 1.0
 * @since 06/04/16
 */
@org.parceler.Parcel
public class NavDrawerModel {

    private static final String TAG = NavDrawerModel.class.getName();

    List<NavDrawerItem> navDrawerItems;

    @Transient
    JsonFileLoader jsonFileLoader;

    public NavDrawerModel() {

    }

    /**
     * Loads nav drawer itetms from json from observable
     *
     * @param context
     * @param jsonFileLoader
     */
    public NavDrawerModel(final Context context, final JsonFileLoader jsonFileLoader) {

        String jsonString = jsonFileLoader
                .getFileAsString(context, R.raw.nav_drawer_items).toBlocking()
                .first();
        Gson gson = new Gson();
        navDrawerItems = Arrays.asList(gson.fromJson(jsonString, NavDrawerItem[].class));

    }

    public List<NavDrawerItem> getNavDrawerItems() {
        return navDrawerItems;
    }

    public void setNavDrawerItems(final List<NavDrawerItem> navDrawerItems) {
        this.navDrawerItems = navDrawerItems;
    }

}
