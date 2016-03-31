package com.overstock.android.prototype.service;

import com.overstock.android.prototype.models.Feed;

import java.util.List;

import rx.Observable;

/**
 * @author LeeMeehan Created on 30-Mar-16.
 */
public interface FeedService {

    Observable<List<Feed>> getFeed();
}
