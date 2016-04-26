package com.overstock.android.prototype.fragment;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.presenter.SignInWithEmailPresenter;
import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * @author gcarroll Fragment used to allow sign-in using email
 */
public class SignInWithEmailFragment extends Fragment implements SignInWithEmailView {

  public static final String TAG = SignInWithEmailFragment.class.getName();

  @Inject
  SignInWithEmailPresenter signInWithEmailPresenter;

  @Bind(R.id.et_username)
  EditText usernameEditText;

  @Bind(R.id.et_password)
  EditText passwordEditText;

  public SignInWithEmailFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_sign_in_with_email, container, false);
    ButterKnife.bind(this, rootView);
    signInWithEmailPresenter.setView(this);
    onDoneKeyPressedListener();
    return rootView;
  }

  @OnClick(R.id.txt_email_sign_up_link)
  public void emailSignUp_onClick() {
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.home_activity, new SignUpWithEmailFragment(), SignUpWithEmailFragment.TAG);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  @OnClick(R.id.btn_email_sign_in_cancel)
  public void onCancelClick() {
    getActivity().onBackPressed();
  }

  @Override
  @OnClick(R.id.btn_sign_in)
  public void OnSignInClick() {
    signInWithEmailPresenter.onSignIn(usernameEditText.getText().toString(), passwordEditText.getText().toString());
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    signInWithEmailPresenter.onDestroy();
  }

  /**
   * This listens to when the Done key is pressed when the user is on the password text box. If pressed the onSignIn
   * method is called in the presenter
   */
  private void onDoneKeyPressedListener() {
    passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          signInWithEmailPresenter.onSignIn(usernameEditText.getText().toString(),
            passwordEditText.getText().toString());
          return true;
        }
        else {
          return false;
        }
      }
    });
  }
}
