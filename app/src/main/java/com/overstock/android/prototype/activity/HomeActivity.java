package com.overstock.android.prototype.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.main.OAppPrototypeApplication;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

  @Inject
  HomeFragment homeFragment;

//  @Override
//  protected void onCreate(final Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_home);
//    ((OAppPrototypeApplication) this.getApplication()).getComponent().inject(this);
//    if (savedInstanceState == null) {
//      getSupportFragmentManager().beginTransaction().add(R.id.home_activity, homeFragment).commit();
//    }
//  }
private GoogleApiClient mGoogleApiClient;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ((OAppPrototypeApplication) this.getApplication()).getComponent().inject(this);
    if (savedInstanceState == null) {
//      final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//              .requestEmail().requestProfile().build();
//      mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
//      checkIfSignedIn();
       getSupportFragmentManager().beginTransaction().add(R.id.home_activity, homeFragment).commit();
    }
  }

  private void checkIfSignedIn() {
    final OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
    if (opr.isDone()) {
      //Log.d(TAG, "Got cached sign-in");
      final GoogleSignInResult result = opr.get();
      handleSignInResult(result);
    }
    else {
      getSupportFragmentManager().beginTransaction().add(R.id.home_activity, homeFragment).commit();
    }
  }

  private void handleSignInResult(final GoogleSignInResult result) {
    //Log.d(TAG, "handleSignInResult:" + result.isSuccess());
    if (result.isSuccess()) {
      // Signed in successfully, show authenticated UI.
      final GoogleSignInAccount acct = result.getSignInAccount();
      Toast.makeText(this, getString(R.string.signed_in_fmt, acct.getDisplayName()), Toast.LENGTH_SHORT).show();
      final Intent signInIntent = new Intent(this, YourInterestsActivity.class);
      startActivity(signInIntent);
    }
    else {
      getSupportFragmentManager().beginTransaction().add(R.id.home_activity, homeFragment).commit();
    }
  }

}
