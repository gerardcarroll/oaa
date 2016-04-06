package com.overstock.android.prototype.view;

import com.overstock.android.prototype.models.Community;

import java.util.List;

/**
 * Created by itowey on 09/03/16.
 */
public interface CommunityView {
    void showCommunities(List<Community> communities);
    List<Community> getCommunities();

}
