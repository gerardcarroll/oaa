package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.BrandFragment;

public class BrandActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_brand);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.brand_activity, new BrandFragment()).commit();
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.transition.slide_back_in_horizontal, R.transition.slide_back_out_horizontal);
  }

}
