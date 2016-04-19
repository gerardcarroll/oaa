package com.overstock.android.prototype.presenter.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.presenter.ConnectWithEmailPresenter;
import com.overstock.android.prototype.view.ConnectWithEmailView;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class ConnectWithEmailPresenterImpl implements ConnectWithEmailPresenter {

    private static final String TAG = ConnectWithEmailPresenterImpl.class.getName();
    private ConnectWithEmailView connectWithEmailView;
    private ParseQuery parseQuery;
    private ParseUser parseUser;
    private Context context;
    private ProgressDialog progressDialog;

    public ConnectWithEmailPresenterImpl(Context context){
        this.context = context;
    }

    @Override
    public void setView(final ConnectWithEmailView connectWithEmailView) {
        this.connectWithEmailView = connectWithEmailView;
    }

    @Override
    public void onSignUp(String username, String password, String passwordConfirm) {

        parseUser = new ParseUser();

        parseUser.setUsername(username);
        parseUser.setPassword(password);
        parseUser.put("passwordConfirm", passwordConfirm);

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    hideProgressDialog();
                    Log.i(TAG, "Successfully signed up to OApp", e);
                    Toast.makeText(context, "User was signed up successfully.", Toast.LENGTH_LONG).show();
                } else {
                    showProgressDialog();
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

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context.getApplicationContext());
            progressDialog.setMessage(context.getResources().getString(R.string.loading));
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}
