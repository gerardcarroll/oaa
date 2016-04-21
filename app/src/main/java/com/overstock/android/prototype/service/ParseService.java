package com.overstock.android.prototype.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.CommunityActivity;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by gcarroll on 21/04/2016.
 */
public class ParseService {

  private static final String TAG = ParseService.class.getName();

  Context context;

  public ParseService(Context context) {
    this.context = context;
    // Parse init
    Parse.initialize(
      new Parse.Configuration.Builder(context).applicationId(context.getString(R.string.parse_application_id))
          .server(context.getString(R.string.parse_service_url)).build());
  }

  public void loginParseUser(String username, String password) {
    ParseUser.logInInBackground(username, password, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if (e == null && user != null) {
          Log.d(TAG, "Username: " + user.getUsername());
          final Intent signInIntent = new Intent(context, CommunityActivity.class);
          signInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(signInIntent);
        }
        else if (user == null) {
          // username or password invalid
          Log.d(TAG, "Username or Password invalid");
        }
        else {
          // Something went wrong
          Log.d(TAG, "Something went wrong with Parse login", e);
        }
      }
    });
  }

  public void signUpNewParseUser(String username, String password) {
    ParseUser parseUser = new ParseUser();
    parseUser.setUsername(username);
    parseUser.setPassword(password);
    parseUser.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          Log.i(TAG, "Successfully signed up to OApp", e);
          // connectWithEmailView.showSignUpSuccess();
          // connectWithEmailView.navigateToCommunity();

        }
        else {
          Log.i(TAG, "Unsuccessful Sign Up to OApp", e);
          // connectWithEmailView.showSignUpError();
        }
      }
    });
  }

}
