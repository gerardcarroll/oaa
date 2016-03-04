package com.overstock.android.prototype.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.CommunitiesAdapter;
import com.overstock.android.prototype.models.Community;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesActivity extends AppCompatActivity {

  @Bind(R.id.btnCommunitySelection)
  SubmitProcessButton progressButton;

  @Bind(R.id.rvCommunities)
  RecyclerView recyclerView;

  @Bind(R.id.oap_toolbar)
  Toolbar toolbar;

  @Bind(R.id.tvToolbarMsg)
  TextView toolBarText;

  @State
  ArrayList<Community> communities;

  private CommunitiesAdapter communitiesAdapter;

  private CollapsingToolbarLayout collapsingToolbarLayout = null;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communities);
    Icepick.restoreInstanceState(this, savedInstanceState);
    ButterKnife.bind(this);

    // Instantiate Toolbar
    setSupportActionBar(toolbar);
    setTitle("");

    android.support.v7.app.ActionBar actionBar = getSupportActionBar();

    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    collapsingToolbarLayout.setTitle("Communities");
    collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.transparent));

    if (communities == null) {
      communities = getData();
    }

    if (savedInstanceState != null) {
      if (savedInstanceState.getInt("button") == 100) {
        progressButton.setEnabled(true);
      }
    }

    // Instantiate the CommunitiesAdapter
    communitiesAdapter = new CommunitiesAdapter(getApplicationContext(), communities);
    // Instantiate Recycler View
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(communitiesAdapter);
    // Setting the LayoutManager for the RecyclerView. Depending on Resolution it will have 2 or 3 columns
    recyclerView
        .setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.communities_columns)));
    recyclerView.stopNestedScroll();
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    // Setup the CommunitiesAdapter Data Change Listener
    setupOnDataChangeListener();
  }

  private void setupOnDataChangeListener() {
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
          progressButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
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

  private ArrayList<Community> getData() {

    final ArrayList<Community> communities = new ArrayList<>();

    final TypedArray typedArray = getResources().obtainTypedArray(R.array.community_image_array);

    final int len = typedArray.length();
    final int[] imagesArray = new int[len];
    for (int i = 0; i < len; i++) {
      imagesArray[i] = typedArray.getResourceId(i, 0);
    }

    final String[] names = getResources().getStringArray(R.array.communities_array);

    for (int i = 0; i < imagesArray.length && i < names.length; i++) {

      final Community community = new Community();

      community.setImageId(imagesArray[i]);
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

    if (id == R.id.action_settings || id == R.id.action_refresh || id == R.id.action_logout) {
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onSaveInstanceState(final Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("button", progressButton.getProgress());
    Icepick.saveInstanceState(this, outState);
  }
}
