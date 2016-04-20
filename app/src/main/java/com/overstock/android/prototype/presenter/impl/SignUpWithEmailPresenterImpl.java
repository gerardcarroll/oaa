package com.overstock.android.prototype.presenter.impl;

import android.util.Log;

import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.view.SignUpWithEmailView;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailPresenterImpl implements SignUpWithEmailPresenter {

    private static final String TAG = SignUpWithEmailPresenterImpl.class.getName();
    private SignUpWithEmailView connectWithEmailView;
    private ParseUser parseUser;

    public SignUpWithEmailPresenterImpl(){}

    @Override
    public void setView(final SignUpWithEmailView connectWithEmailView) {
        this.connectWithEmailView = connectWithEmailView;
    }

    @Override
    public void onSignUp(final String username, final String password) {

        parseUser = new ParseUser();

        parseUser.setUsername(username);
        parseUser.setPassword(password);

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i(TAG, "Successfully signed up to OApp", e);
                    connectWithEmailView.showSignUpSuccess();
                    connectWithEmailView.navigateToCommunity();

                } else {
                    Log.i(TAG, "Unsuccessful Sign Up to OApp", e);
                    connectWithEmailView.showSignUpError();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        connectWithEmailView = null;
    }
}
