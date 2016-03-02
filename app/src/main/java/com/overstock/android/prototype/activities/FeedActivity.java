package com.overstock.android.prototype.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.overstock.android.prototype.R;

/**
 * Created by rconnolly on 3/1/2016.
 */
public class FeedActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_feed);

    Toast.makeText(this, "You made it!!", Toast.LENGTH_SHORT).show();
  }
}
