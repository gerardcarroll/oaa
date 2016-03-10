package com.overstock.android.prototype.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.overstock.android.prototype.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourInterestsActivity extends AppCompatActivity {

    @Bind(R.id.your_interests_title)
    TextView yourInterestsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_interests);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.your_interests_nfl_btn)
    public void onNflBtnClick(){
        Intent signInIntent = new Intent(this,BrandActivity.class);
        startActivity(signInIntent);
        Toast.makeText(getApplicationContext(), "TODO: redirect to 'Brand Activity'", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.your_interests_communities_btn)
    public void onCommunitiesBtnClick(){
        Intent signInIntent = new Intent(this,CommunitiesActivity.class);
        startActivity(signInIntent);

    }

}
