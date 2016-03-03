package com.overstock.android.prototype.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.CommunitiesAdapter;
import com.overstock.android.prototype.models.Community;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesActivity extends AppCompatActivity {

  @Bind(R.id.btnCommunitySelection)
  SubmitProcessButton progressButton;

  private RecyclerView recyclerView;

  private CommunitiesAdapter communitiesAdapter;

  private Toolbar toolbar;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communities);
    ButterKnife.bind(this);

    // Instantiate Toolbar
    toolbar = (Toolbar) findViewById(R.id.oap_toolbar);
    setSupportActionBar(toolbar);
    setTitle("");

    // Universal Image Loader configuration
    DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .defaultDisplayImageOptions(defaultOptions)
            .build();
    ImageLoader.getInstance().init(config);

    // Instantiate Recycler View
    recyclerView = (RecyclerView) findViewById(R.id.rvCommunities);
    recyclerView.setHasFixedSize(true);
    communitiesAdapter = new CommunitiesAdapter(getApplicationContext(), getData());
    recyclerView.setAdapter(communitiesAdapter);
    recyclerView
        .setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.communities_columns)));
    recyclerView.stopNestedScroll();
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    // Set the Button to disabled initially
    progressButton.setEnabled(false);

    // implement the listener for the communities adapter to update the progress button
    communitiesAdapter.setOnDataChangeListener(new CommunitiesAdapter.OnDataChangeListener() {
      @Override
      public void onDataChanged(final int size) {
        if (size >= 3) {
          progressButton.setProgress(100);
        }
        else if (size > 0) {
          final double progress = (size / 3.0) * 100;
          progressButton.setProgress((int) Math.ceil(progress));
        }
        else {
          progressButton.setProgress(0);
        }

        if (progressButton.getProgress() == 100) {
          progressButton.setEnabled(true);
        }
        else {
          progressButton.setEnabled(false);
        }
      }
    });
  }

  @OnClick(R.id.btnCommunitySelection)
  public void btnCommunitiesSelected() {
    final Intent intent = new Intent(this, FeedActivity.class);
    startActivity(intent);
  }

  private List<Community> getData() {

    final List<Community> communities = new ArrayList<>();

    final TypedArray typedArray = getResources().obtainTypedArray(R.array.community_image_array);
    int len = typedArray.length();
    int[] extImagesArray = new int[len];
    for ( int i = 0; i < len; i++) {
      extImagesArray[i] = typedArray.getResourceId(i, 0);
    }

//    final int[] inyImagesArray = new int[]{ R.drawable.man, R.drawable.woman, R.drawable.man_and_woman,
//                                    R.drawable.home_decor, R.drawable.games, R.drawable.leisure,
//                                    R.drawable.family, R.drawable.gadgets, R.drawable.furniture,
//                                    R.drawable.bedding, R.drawable.fitness, R.drawable.home_automation,
//                                    R.drawable.mobility, R.drawable.personal_care, R.drawable.shoes,
//                                    R.drawable.sports_shoes, R.drawable.technology, R.drawable.watches};

    final String[] names = getResources().getStringArray(R.array.communities_array);

    for (int i = 0; i < extImagesArray.length && i < names.length; i++) {

      final Community community = new Community();

      community.setImageId(extImagesArray[i]);
      community.setName(names[i]);

      communities.add(community);
    }

    return communities;
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {

    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {

    final int id = item.getItemId();

    if (id == R.id.action_settings || id == R.id.action_refresh) {
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
