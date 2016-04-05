package com.overstock.android.prototype.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.presenter.CommunitiesPresenter;
import com.overstock.android.prototype.presenter.CommunitiesPresenterImpl;
import com.overstock.android.prototype.view.CommunitiesView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

/**
 * Activity class to display data via the communities recycler view, presented to it through the
 * {@link CommunitiesPresenterImpl} class.
 *
 * @author RayConnolly Created on 2/29/2016.
 */
public class CommunitiesActivity extends AppCompatActivity implements CommunitiesView {

  private static final int ONE_HUNDRED = 100;

  @Bind(R.id.collapsing_toolbar)
  CollapsingToolbarLayout collapsingToolbarLayout;

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

  private CommunitiesAdapter communitiesAdapter;

  @Inject
  CommunitiesPresenter communitiesPresenter;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communities);

    ((OAppPrototypeApplication) this.getApplication()).getComponent().inject(this);

    Icepick.restoreInstanceState(this, savedInstanceState);

    ButterKnife.bind(this);

    communitiesAdapter = new CommunitiesAdapter(getApplicationContext());

    setSupportActionBar(toolbar);
    setTitle("");

    collapsingToolbarLayout.setTitle(getString(R.string.communities_activity_title));
    collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.transparent));

    // Populate communities via presenter class
    communitiesPresenter.setView(this);
    communitiesPresenter.populateAndShowCommunities();

    //TODO Fix save state issue on orientation change
    //TODO Look at extracting state changes to a private method
    if (savedInstanceState != null) {
      if (savedInstanceState.getInt("button") == ONE_HUNDRED) {
        progressButton.setEnabled(true);
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    communitiesPresenter.destroyView();
  }

  @Override
  public void showCommunities(final List<Community> communities) {
    communitiesAdapter.setData(communities);
    // Set recycler view settings
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(communitiesAdapter);
    // Setting a GridLayoutManager for the RecyclerView, which dependent on screen resolution will have 2 or 3 columns
    recyclerView.setLayoutManager(new GridLayoutManager(this, numCommunitiesColumns));
    recyclerView.stopNestedScroll();
    // Setup the CommunitiesAdapter Data Change Listener
    setupOnDataChangeListener();
  }

  /**
   * Method to implement progress and animation functionality for the Progress Button
   */
  private void setupOnDataChangeListener() {
    // Implement the listener for the communities adapter to update the progress button
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
        } else {
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

  /**
   * Method to allow navigation to Feed activity (using a slide transition) when Progress button is enabled and pressed.
   */
  @OnClick(R.id.btnCommunitySelection)
  public void btnCommunitiesSelected() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(this,
      R.transition.slide_in_vertical, R.transition.slide_out_vertical);
    startActivity(new Intent(this, FeedActivity.class), options.toBundle());
  }

  @Override
  public void onSaveInstanceState(final Bundle outState) {
    super.onSaveInstanceState(outState);
    //outState.putInt("button", progressButton.getProgress());
    Icepick.saveInstanceState(this, outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Icepick.restoreInstanceState(this, savedInstanceState);
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
}
