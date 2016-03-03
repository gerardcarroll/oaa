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
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.SubmitProcessButton;
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

  private TextView toolBarText;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communities);
    ButterKnife.bind(this);

//    final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);

    // Instantiate Toolbar
    toolbar = (Toolbar) findViewById(R.id.oap_toolbar);
    setSupportActionBar(toolbar);
    setTitle("");
    toolBarText = (TextView) findViewById(R.id.tvToolbarMsg);
    toolBarText.setText(R.string.communitiesToolbarText);

    // Instantiate Recycler View
    recyclerView = (RecyclerView) findViewById(R.id.rvCommunities);
    recyclerView.setHasFixedSize(true);
    communitiesAdapter = new CommunitiesAdapter(getApplicationContext(), getData());
    recyclerView.setAdapter(communitiesAdapter);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
        } else if (size > 0) {
          final double progress = (size / 3.0) * 100;
          progressButton.setProgress((int) Math.ceil(progress));
        } else {
          progressButton.setProgress(0);
        }

        if (progressButton.getProgress() == 100) {
          progressButton.setEnabled(true);
        } else {
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
    int[] imagesArray = new int[len];
    for ( int i = 0; i < len; i++) {
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

    if (id == R.id.action_settings || id == R.id.action_refresh) {
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
