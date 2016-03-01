package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.overstock.android.prototype.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author LeeMeehan
 */
public class HomeFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @OnClick(R.id.googlePlus_login_btn)
  public void googlePlusLogin_onClick() {
    // TODO added Login logic.
  }

  @OnClick(R.id.facebook_login_btn)
  public void faceBookLogin_onClick() {
    Toast.makeText(getActivity(), R.string.faceBook_login_toast, Toast.LENGTH_LONG).show();
  }

  @OnClick(R.id.guest_login_btn)
  public void guestLogin_onClick() {
    Toast.makeText(getActivity(), R.string.guest_login_toast, Toast.LENGTH_LONG).show();
  }

}
