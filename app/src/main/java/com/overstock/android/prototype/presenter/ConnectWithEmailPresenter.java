package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.ConnectWithEmailView;

/**
 * Created by rconnolly on 4/19/2016.
 */
public interface ConnectWithEmailPresenter {

    void setView(final ConnectWithEmailView connectWithEmailView);

    void onSignUp(String username, String password);

    void onDestroy();
}
