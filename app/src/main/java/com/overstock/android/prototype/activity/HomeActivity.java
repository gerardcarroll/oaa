package com.overstock.android.prototype.activity;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.expresso.component.HomeActivityComponent;
import com.overstock.android.prototype.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

  @Inject
  HomeFragment homeFragment;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    // Dagger init
    HomeActivityComponent.Initializer.init().inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.home_activity, homeFragment,HomeFragment.TAG).commit();
    }
  }
}
