package com.overstock.android.prototype.view;

/**
 * Created by gcarroll on 20/04/2016.
 */
public interface SignInWithEmailView {

  void onSignInClick();

  void displayToast(final String toastMessage);

  void navigateToCommunity();
}