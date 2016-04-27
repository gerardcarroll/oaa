package com.overstock.android.prototype.service;

import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.view.SignInWithEmailView;
import com.overstock.android.prototype.view.SignUpWithEmailView;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by itowey, gcarroll on 21/04/2016.
 */
public class ParseService {

  private static final String TAG = ParseService.class.getName();

  Context context;

  public ParseService(Context context) {
    this.context = context;
  }

  public void loginParseUser(String username, String password, final SignInWithEmailView view) {
    ParseUser.logInInBackground(username, password, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if (e == null && user != null) {
          String message = "Email Sign In as: ";
          Log.d(TAG, message + user.getUsername());
          view.displayToast(message + user.getUsername());
          view.navigateToCommunity();
        }
        else if (user == null) {
          // username or password invalid
          String message = "Username or Password invalid.";
          Log.d(TAG, message);
          view.displayToast(message);
        }
        else {
          // Something went wrong
          String message = "Error with Parse login. ";
          Log.d(TAG, message, e);
          view.displayToast(message + e.getMessage());
        }
      }
    });
  }

  public void signUpNewParseUser(String username, String password, final SignUpWithEmailView view) {
    ParseUser parseUser = new ParseUser();
    parseUser.setUsername(username);
    parseUser.setPassword(password);
    parseUser.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          String message = "Successfully signed up using Email. ";
          Log.d(TAG, message);
          view.displayToast(message);
          view.navigateToCommunity();
        }
        else {
          String message = "Unsuccessful Sign Up using Email. ";
          Log.d(TAG, message + e.getMessage());
          view.displayToast(message + e.getMessage());
        }
      }
    });
  }
}
