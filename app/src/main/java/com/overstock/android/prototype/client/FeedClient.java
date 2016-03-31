package com.overstock.android.prototype.client;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func0;
import android.content.Context;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Feed;
import com.overstock.android.prototype.service.FeedService;

/**
 * @author LeeMeehan Created on 30-Mar-16.
 */
public class FeedClient {

  public static FeedService getClient(final Context context){
        return new FeedService() {
            @Override
            public Observable<List<Feed>> getFeed() {
                final List<Feed> feeds = new ArrayList<>();
                // TODO only using this for now to populate recycler view
                // TODO set up feed specific data and implement using presenter class
                final String[] imageReferenceArray = context.getResources().getStringArray(R.array.feed_image_array);

                final int len = imageReferenceArray.length;
                final int[] imagesArray = new int[len];
                for (int i = 0; i < len; i++) {
                    imagesArray[i] = context.getResources().getIdentifier(imageReferenceArray[i], "drawable",
                            context.getPackageName());
                }

                feeds.add(new Feed(imagesArray[0], "Top NFL Fan Products for 2016", "NFL.com"));
                feeds.add(new Feed(imagesArray[1], "Get Your Beat on!, with Beats by dre", "DreBeats.com"));
                feeds.add(new Feed(imagesArray[2], "Treat yourself. Top skin care products of this month.", "Relax.com"));
                feeds.add(new Feed(imagesArray[3], "Must Have Products to get in Shape.", "GetFit.com"));
                // TODO remove up as far as last todo
                /*The defer Observer allows you to defer or delay emitting items from an Observable until such time as an
                Observer subscribes to the Observable.*/
                return Observable.defer(new Func0<Observable<List<Feed>>>() {
                    @Override
                    public Observable<List<Feed>> call() {
                        return Observable.just(feeds);
                    }
                });
            }
        };
    }
}
