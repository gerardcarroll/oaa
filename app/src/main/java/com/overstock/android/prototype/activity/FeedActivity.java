package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.overstock.android.prototype.R;

/**
 * Created by rconnolly on 3/1/2016.
 */
public class FeedActivity extends AppCompatActivity {

  private Toolbar toolbar;

  private TextView toolBarText;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_feed);

    // Instantiate Toolbar
    toolbar = (Toolbar) findViewById(R.id.oap_toolbar);
    setSupportActionBar(toolbar);
    setTitle("");
    toolBarText = (TextView) findViewById(R.id.tvToolbarMsg);
    toolBarText.setText(R.string.feedToolbarText);

    Toast.makeText(this, "You made it!!", Toast.LENGTH_SHORT).show();
  }
}
