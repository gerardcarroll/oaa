package com.overstock.android.prototype.presenter;

import android.content.Context;
import android.content.res.TypedArray;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.view.CommunitiesMvpView;
import com.overstock.android.prototype.models.Community;

import java.util.ArrayList;

/**
 * Created by itowey on 08/03/16.
 */
public class CommunitiesPresenter {

    private CommunitiesMvpView communitiesMvpView;

    private Context context;

    public CommunitiesPresenter(Context context) {
        this.context = context;
    }

    public void attachedView(CommunitiesMvpView communitiesMvpView){
        this.communitiesMvpView = communitiesMvpView;
    };

    public void detachView(CommunitiesMvpView communitiesMvpView){
        this.communitiesMvpView = null;
    };

    public void populateAndShowCommunities(){

        //start TODO: below block to be replaced by data service call
        final ArrayList<Community> communities = new ArrayList<>();

        final TypedArray typedArray = context.getResources().obtainTypedArray(R.array.community_image_array);

        final int len = typedArray.length();
        final int[] imagesArray = new int[len];
        for (int i = 0; i < len; i++) {
            imagesArray[i] = typedArray.getResourceId(i, 0);
        }

        final String[] names = context.getResources().getStringArray(R.array.communities_array);

        for (int i = 0; i < imagesArray.length && i < names.length; i++) {

            final Community community = new Community();
            community.setImageId(imagesArray[i]);
            community.setName(names[i]);

            communities.add(community);
        }
        //end TODO
        communitiesMvpView.showCommunities(communities);
    }
}
