package com.overstock.android.prototype.service;

import com.overstock.android.prototype.models.Community;

import java.util.List;

import rx.Observable;

/**
 * @author RayConnolly Created on 4/1/2016.
 */
public interface CommunityService {

    Observable<List<Community>> getCommunities();
}
