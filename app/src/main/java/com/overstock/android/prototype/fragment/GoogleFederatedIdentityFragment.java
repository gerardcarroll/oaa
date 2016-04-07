package com.overstock.android.prototype.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.CommunityActivity;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.service.OappGoogleAuthService;

import javax.inject.Inject;

/**
 * @author itowey Fragment used to allow sign-in using google login api
 */
public class GoogleFederatedIdentityFragment extends Fragment {

    public  static final String TAG = GoogleFederatedIdentityFragment.class.getName();

    private static final int RC_SIGN_IN = 9001;

    @Inject OappGoogleAuthService oappGoogleAuthService;

    private ProgressDialog mProgressDialog;

    public GoogleFederatedIdentityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
        signIn();
    }

    @Override
    public void onStop() {
        if (oappGoogleAuthService.getGoogleApiClient() != null && oappGoogleAuthService.getGoogleApiClient().isConnected()) {
            oappGoogleAuthService.getGoogleApiClient().disconnect();
        }
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (oappGoogleAuthService.getGoogleApiClient() != null) {
            oappGoogleAuthService.getGoogleApiClient().connect();
        }

        final OptionalPendingResult<GoogleSignInResult> opr = oappGoogleAuthService.silentSignInStatus();

        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            final GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(final GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            final GoogleSignInResult result = oappGoogleAuthService.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(final GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            final GoogleSignInAccount acct = result.getSignInAccount();
            final Toast toast = Toast.makeText(this.getContext(), getString(R.string.signed_in_fmt, acct.getDisplayName()),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 20);
            toast.show();

            final Intent signInIntent = new Intent(getActivity(), CommunityActivity.class);
            startActivity(signInIntent);
        } else {
            Toast.makeText(this.getContext(), "Not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void signIn() {
        final Intent signInIntent = oappGoogleAuthService.signIn();
        if (signInIntent != null) {
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this.getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public String toString() {
        return "";
    }

}