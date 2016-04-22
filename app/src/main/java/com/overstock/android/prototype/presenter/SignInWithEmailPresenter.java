package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * Created by gcarroll on 20/04/2016.
 */
public interface SignInWithEmailPresenter {

  void setView(final SignInWithEmailView emailSignInView);

  void onSignIn(final String username, final String password);

  void onDestroy();
}
