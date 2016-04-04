package com.overstock.android.prototype.interfaces;

import android.content.Context;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.service.CommunityService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by rconnolly on 4/1/2016.
 */
public class CommunityClient {

    public static CommunityService getClient(final Context context){

        return new CommunityService() {
            @Override
            public Observable<List<Community>> getCommunities() {

                final List<Community> communities = new ArrayList<>();

                final String[] imageReferenceArray = context.getResources().getStringArray(R.array.community_image_array);

                final int len = imageReferenceArray.length;
                final int[] imagesResourceArray = new int[len];
                for (int i = 0; i < len; i++) {
                    imagesResourceArray[i] = context.getResources().getIdentifier(imageReferenceArray[i], "drawable",
                            context.getPackageName());
                }

                final String[] names = context.getResources().getStringArray(R.array.communities_array);

                for (int i = 0; i < imagesResourceArray.length && i < names.length; i++) {

                    final Community community = new Community();
                    community.setImageId(imagesResourceArray[i]);
                    community.setName(names[i]);

                    communities.add(community);
                }

                return Observable.defer(new Func0<Observable<List<Community>>>() {
                    @Override
                    public Observable<List<Community>> call() {
                        return Observable.just(communities);
                    }
                });
            }
        };
    }
}
