package com.overstock.android.prototype.view;

/**
 * Created by rconnolly on 4/19/2016.
 */
public interface ConnectWithEmailView {

    void signIn(String username, String password);

    void signUp(String username, String password, String passwordConfirm);
}
