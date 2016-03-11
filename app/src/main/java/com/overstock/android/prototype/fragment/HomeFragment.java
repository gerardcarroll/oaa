package com.overstock.android.prototype.fragment;

import android.os.Bundle;
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

/**
 * @author LeeMeehan
 */
public class HomeFragment extends Fragment {

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @OnClick(R.id.googlePlus_login_btn)
  public void googlePlusLogin_onClick() {
    final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.home_activity, new GoogleFederatedIdentityFragment());
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  @OnClick(R.id.facebook_login_btn)
  public void faceBookLogin_onClick() {
    final Toast toast = Toast.makeText(getActivity(), "FaceBook Login Coming Soon!", Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 0);
    toast.show();
  }

  @OnClick(R.id.guest_login_btn)
  public void guestLogin_onClick() {
    final Toast toast = Toast.makeText(getActivity(), "Guest Login Coming Soon!", Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 0);
    toast.show();
  }

}
