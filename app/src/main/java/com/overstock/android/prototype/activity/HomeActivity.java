package com.overstock.android.prototype.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.R;

public class HomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.home_activity, new HomeFragment()).commit();
    }
  }
}
