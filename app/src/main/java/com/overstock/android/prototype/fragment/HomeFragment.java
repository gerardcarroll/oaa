package com.overstock.android.prototype.fragment;

import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.CommunityActivity;
import com.overstock.android.prototype.component.HomeActivityComponent;

/**
 * @author LeeMeehan
 */
public class HomeFragment extends Fragment {

  public static String TAG = HomeFragment.class.getName();

  @Inject
  GoogleFederatedIdentityFragment googleFederatedIdentityFragment;

  @Inject
  EmailSignInFragment emailSignInFragment;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, rootView);
    // Dagger init
    HomeActivityComponent.Initializer.init().inject(this);
    return rootView;
  }

  @OnClick(R.id.googlePlus_login_btn)
  public void googlePlusLogin_onClick() {
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.home_activity, googleFederatedIdentityFragment, GoogleFederatedIdentityFragment.TAG);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  @OnClick(R.id.facebook_login_btn)
  public void faceBookLogin_onClick() {
    final Toast toast = Toast.makeText(getActivity(), "FaceBook Login Coming Soon!", Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 20);
    toast.show();
  }

  @OnClick(R.id.email_login_btn)
  public void emailLogin_onClick() {
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.home_activity, emailSignInFragment, EmailSignInFragment.TAG);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  @OnClick(R.id.guest_login_btn)
  public void guestLogin_onClick() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getContext(),
      R.transition.slide_in_vertical, R.transition.slide_out_vertical);
    startActivity(new Intent(getActivity(), CommunityActivity.class), options.toBundle());
  }

}
