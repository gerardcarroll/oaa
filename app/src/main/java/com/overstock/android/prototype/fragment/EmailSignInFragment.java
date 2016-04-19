package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;

/**
 * @author gcarroll Fragment used to allow sign-in using email
 */
public class EmailSignInFragment extends Fragment {

  public static final String TAG = EmailSignInFragment.class.getName();

  public EmailSignInFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_email_sign_in, container, false);
  }
}
