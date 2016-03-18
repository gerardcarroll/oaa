package com.overstock.android.prototype.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;

/**
 * This Activity is only for demonstration of Shared Element Transition and
 * <a href="https://developer.android.com/training/transitions/index.html">Scene Transitions</a>
 */
public class SharedElementActivity extends AppCompatActivity {

  Scene firstScene;

  Scene secondScene;

  Transition mFadeTransition;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shared_element);
    ButterKnife.bind(this);

    final Bundle extras = getIntent().getExtras();
    final byte[] b = extras.getByteArray("image");

    final Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
    final ImageView image = (ImageView) findViewById(R.id.shared_activity_image_1);
    image.setImageBitmap(bmp);

    // Create the scene root for the scenes in this app
    final ViewGroup mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);

    // Create the scenes
    firstScene = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_shared_element_scene_1, this);
    secondScene = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_shared_element_scene_2, this);

    mFadeTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);
  }

  @OnClick(R.id.transition_button)
  public void BtnOnClick() {
    TransitionManager.go(secondScene, mFadeTransition);
  }
}
