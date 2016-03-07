package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;

/**
 * Created by rconnolly on 3/1/2016.
 */
public class FeedActivity extends AppCompatActivity {

  @Bind(R.id.oap_toolbar)
  Toolbar toolbar;

  @Bind(R.id.tvToolbarMsg)
  TextView toolBarText;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_feed);
    ButterKnife.bind(this);

    // Instantiate Toolbar
    setSupportActionBar(toolbar);
    setTitle("");
    toolBarText.setText(R.string.feedToolbarText);

    Toast.makeText(this, "You made it!!", Toast.LENGTH_SHORT).show();
  }
}
