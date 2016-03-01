package com.overstock.android.prototype.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

  private CommunitiesAdapter communitiesAdapter;

  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_communities);

    toolbar = (Toolbar)findViewById(R.id.oap_toolbar);
    setSupportActionBar(toolbar);

    recyclerView = (RecyclerView) findViewById(R.id.rvCommunities);
    recyclerView.setHasFixedSize(true);
    communitiesAdapter = new CommunitiesAdapter(getApplicationContext(), getData());
    recyclerView.setAdapter(communitiesAdapter);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    recyclerView.stopNestedScroll();
    recyclerView.setItemAnimator(new DefaultItemAnimator());
  }

  private List<Community> getData() {

    List<Community> communities = new ArrayList<>();
    int[] images = { R.drawable.man, R.drawable.woman, R.drawable.man_and_woman, R.drawable.home_decor,
        R.drawable.games, R.drawable.leisure, R.drawable.family, R.drawable.gadgets, R.drawable.games,
        R.drawable.leisure, R.drawable.family, R.drawable.gadgets };
    String[] names = getResources().getStringArray(R.array.communities_array);

    for (int i = 0; i < images.length && i < names.length; i++) {

      Community community = new Community();

      community.setImageId(images[i]);
      community.setName(names[i]);

      communities.add(community);
    }

    return communities;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    int id = item.getItemId();

    if (id == R.id.action_settings || id == R.id.action_refresh){
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
