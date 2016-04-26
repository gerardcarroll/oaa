package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.SignUpWithEmailView;

/**
 * Created by rconnolly on 4/19/2016.
 */
public interface SignUpWithEmailPresenter {

    void setView(final SignUpWithEmailView connectWithEmailView);

    void onSignUp(final String username, final String password);

    void onDestroy();

    void validateCredentials(final String username, final String password, final String confirmPassword);
}
