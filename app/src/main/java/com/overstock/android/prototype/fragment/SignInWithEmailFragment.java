package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;

/**
 * @author gcarroll Fragment used to allow sign-in using email
 */
public class SignInWithEmailFragment extends Fragment {

  public static final String TAG = SignInWithEmailFragment.class.getName();

  public SignInWithEmailFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_sign_in_with_email, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @OnClick(R.id.txt_email_sign_up)
  public void emailSignUp_onClick() {
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.home_activity, new SignUpWithEmailFragment(), SignUpWithEmailFragment.TAG);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }
}
