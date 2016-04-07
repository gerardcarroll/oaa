package com.overstock.android.prototype.service;

import java.util.List;

import rx.Observable;

import com.overstock.android.prototype.model.Community;

/**
 * @author RayConnolly Created on 4/1/2016.
 */
public interface CommunityService {

  Observable<List<Community>> getCommunities();
}
