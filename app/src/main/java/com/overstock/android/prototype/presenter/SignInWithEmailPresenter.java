package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * Created by gcarroll on 20/04/2016.
 */
public interface SignInWithEmailPresenter {

  void setView(SignInWithEmailView emailSignInView);

  void onSignIn(String username, String password);

  void onSignInError(String message);

  void onDestroy();
}
