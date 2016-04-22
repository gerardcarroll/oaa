package com.overstock.android.prototype.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.overstock.android.prototype.activity.CommunityActivity;
import com.parse.LogInCallback;
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

  public void loginParseUser(String username, String password) {
    ParseUser.logInInBackground(username, password, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if (e == null && user != null) {
          Log.d(TAG, "Username: " + user.getUsername());
          // TODO Remove Toast
          Toast.makeText(context, "Signing in as : " + user.getUsername(), Toast.LENGTH_SHORT).show();
          final Intent signInIntent = new Intent(context, CommunityActivity.class);
          signInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(signInIntent);
        }
        else if (user == null) {
          // username or password invalid
          Log.d(TAG, "Username or Password invalid");
          // TODO Remove Toast
          Toast.makeText(context, "Username or Password Invalid", Toast.LENGTH_SHORT).show();
        }
        else {
          // Something went wrong
          Log.d(TAG, "Error with Parse login", e);
          // TODO Remove Toast
          Toast.makeText(context, "Username or Password Invalid", Toast.LENGTH_SHORT).show();
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
          Log.d(TAG, "Successfully signed up using Parse");
          // TODO Remove Toast
          Toast.makeText(context, "Successfully signed up using Parse", Toast.LENGTH_SHORT).show();
          final Intent signInIntent = new Intent(context, CommunityActivity.class);
          signInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(signInIntent);
          // connectWithEmailView.showSignUpSuccess();
          // connectWithEmailView.navigateToCommunity();
        }
        else {
          Log.d(TAG, "Unsuccessful Sign Up using Parse", e);
          // TODO Remove Toast
          Toast.makeText(context, "Unsuccessful Sign Up using Parse", Toast.LENGTH_SHORT).show();
          // connectWithEmailView.showSignUpError();
        }
      }
    });
  }
}
