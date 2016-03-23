package com.overstock.android.prototype.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.CommunitiesAdapter;
import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.presenter.CommunitiesPresenter;
import com.overstock.android.prototype.view.CommunitiesMvpView;

import icepick.Icepick;
import icepick.State;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesActivity extends AppCompatActivity implements CommunitiesMvpView {

  private static final int ONE_HUNDRED = 100;

  @Bind(R.id.btnCommunitySelection)
  SubmitProcessButton progressButton;

  @Bind(R.id.rvCommunities)
  RecyclerView recyclerView;

  @Bind(R.id.oap_toolbar)
  Toolbar toolbar;

  @Bind(R.id.tvToolbarMsg)
  TextView toolBarText;

  @BindInt(R.integer.min_selected_communities)
  int minSelectedCommunities;

  @BindInt(R.integer.communities_columns)
  int numCommunitiesColumns;

  @State
  ArrayList<Community> communities;

  private CommunitiesAdapter communitiesAdapter;

  private CommunitiesPresenter communitiesPresenter;

  private CollapsingToolbarLayout collapsingToolbarLayout = null;

  public CommunitiesActivity() {
    communitiesPresenter = new CommunitiesPresenter(this);
    communitiesPresenter.attachedView(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    communitiesPresenter.attachedView(this);
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communities);
    Icepick.restoreInstanceState(this, savedInstanceState);
    ButterKnife.bind(this);

    communitiesAdapter = new CommunitiesAdapter(getApplicationContext());

    // Instantiate Toolbar
    setSupportActionBar(toolbar);
    setTitle("");

    final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    collapsingToolbarLayout.setTitle(getString(R.string.communities_activity_title));
    collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.transparent));

    communitiesPresenter.populateAndShowCommunities();

    if (savedInstanceState != null) {
      if (savedInstanceState.getInt("button") == ONE_HUNDRED) {
        progressButton.setEnabled(true);
      }
    }
  }

  private void setupOnDataChangeListener() {
    // implement the listener for the communities adapter to update the progress button
    communitiesAdapter.setOnDataChangeListener(new CommunitiesAdapter.OnDataChangeListener() {

      @Override
      public void onDataChanged(final int size) {

        final int progress = Math.min((int) Math.ceil(((double) size / minSelectedCommunities) * ONE_HUNDRED),
          ONE_HUNDRED);
        progressButton.setProgress(progress);

        if (progressButton.getProgress() == ONE_HUNDRED) {
          if (!progressButton.isEnabled()) {
            progressButton
                .startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.continue_btn_bounce));
          }
          progressButton.setAlpha(1f);
          progressButton.setEnabled(true);
        }
        else {
          if (progressButton.isEnabled()) {
            progressButton.startAnimation(
              AnimationUtils.loadAnimation(getApplicationContext(), R.anim.continue_btn_bounce_revert));
          }
          progressButton.setAlpha(0.75f);
          progressButton.setEnabled(false);
        }
      }
    });
  }

  @OnClick(R.id.btnCommunitySelection)
  public void btnCommunitiesSelected() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(this,
      R.transition.slide_in_vertical, R.transition.slide_out_vertical);
    startActivity(new Intent(this, FeedActivity.class), options.toBundle());
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

  @Override
  public void showCommunities(final List<Community> communities) {
    // Instantiate the CommunitiesAdapter
    // Instantiate Recycler View
    communitiesAdapter.setData(communities);
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(communitiesAdapter);
    // Setting the LayoutManager for the RecyclerView. Depending on Resolution it will have 2 or 3 columns
    recyclerView.setLayoutManager(new GridLayoutManager(this, numCommunitiesColumns));
    recyclerView.stopNestedScroll();
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    // Setup the CommunitiesAdapter Data Change Listener
    setupOnDataChangeListener();
  }
}
