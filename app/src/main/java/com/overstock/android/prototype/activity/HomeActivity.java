package com.overstock.android.prototype.activity;

import javax.inject.Inject;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AnimationUtils;

import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

  @Inject
  HomeFragment homeFragment;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ((OAppPrototypeApplication) this.getApplication()).getComponent().inject(this);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.home_activity, homeFragment).commit();
    }
  }

}
