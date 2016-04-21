package com.overstock.android.prototype.view;

/**
 * Created by rconnolly on 4/19/2016.
 */
public interface SignUpWithEmailView {

    void showSignUpSuccess();

    void showSignUpError();

    void displayToast(final String toastMessage);

    void OnSignUpClick();

    void navigateToCommunity();
}
