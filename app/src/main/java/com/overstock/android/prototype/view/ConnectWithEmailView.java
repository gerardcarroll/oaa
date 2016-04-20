package com.overstock.android.prototype.view;

/**
 * Created by rconnolly on 4/19/2016.
 */
public interface ConnectWithEmailView {

    void showSignUpSuccess();

    void showSignUpError();

    void displayToast(String toastMessage);

    void OnSignUpClick();
}
