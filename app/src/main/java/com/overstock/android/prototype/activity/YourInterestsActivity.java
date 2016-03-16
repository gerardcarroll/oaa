package com.overstock.android.prototype.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;

public class YourInterestsActivity extends AppCompatActivity {

  @Bind(R.id.your_interests_title)
  TextView yourInterestsTitle;

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.transition.slide_back_in_vertical, R.transition.slide_back_out_vertical);
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_your_interests);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.your_interests_nfl_btn)
  public void onNflBtnClick() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(this,
      R.transition.slide_in_horizontal, R.transition.slide_out_horizontal);
    startActivity(new Intent(this, BrandActivity.class), options.toBundle());
  }

  @OnClick(R.id.your_interests_communities_btn)
  public void onCommunitiesBtnClick() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(this,
      R.transition.slide_in_vertical, R.transition.slide_out_horizontal);
    startActivity(new Intent(this, CommunitiesActivity.class), options.toBundle());
  }

}
