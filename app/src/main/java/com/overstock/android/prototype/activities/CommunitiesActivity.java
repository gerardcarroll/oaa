package com.overstock.android.prototype.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.CommunitiesAdapter;
import com.overstock.android.prototype.models.Community;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardView cardView;
    private CommunitiesAdapter communitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_communities);

        cardView = (CardView)findViewById(R.id.cvCommunities);

        recyclerView = (RecyclerView) findViewById(R.id.rvCommunities);
        recyclerView.setHasFixedSize(true);
        communitiesAdapter = new CommunitiesAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(communitiesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private static List<Community> getData(){

        List<Community> communities = new ArrayList<>();
        int [] images = { R.drawable.man, R.drawable.woman, R.drawable.man_and_woman, R.drawable.home_decor, R.drawable.games, R.drawable.leisure, R.drawable.family, R.drawable.gadgets }; // TODO images to be added to array
        String [] names = { "Men", "Women", "Men & Women", "Home Decor", "Games", "Leisure", "Family", "Gadgets"}; // TODO names to be added to array

        for ( int i = 0; i < images.length && i < names.length; i++){

            Community community = new Community();

            community.setImageId(images[i]);
            community.setName(names[i]);

            communities.add(community);
            }

            return communities;
        }
}
