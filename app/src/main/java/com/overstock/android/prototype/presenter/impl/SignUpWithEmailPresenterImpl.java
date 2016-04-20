package com.overstock.android.prototype.presenter.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.view.SignUpWithEmailView;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailPresenterImpl implements SignUpWithEmailPresenter {

    private static final String TAG = SignUpWithEmailPresenterImpl.class.getName();
    private SignUpWithEmailView connectWithEmailView;
    private ParseQuery parseQuery;
    private ParseUser parseUser;
    private Context context;

    public SignUpWithEmailPresenterImpl(Context context){
        this.context = context;
    }

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
                    Toast.makeText(context, "User was signed up successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Log.i(TAG, "Unsuccessful Sign Up to OApp", e);
                    Toast.makeText(context, "Unable to sign up user.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    public void onDestroy() {
        connectWithEmailView = null;
    }
}
