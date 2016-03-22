package com.overstock.android.prototype.service;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import javax.inject.Inject;

/**
 * Created by itowey on 14/03/16.
 */
public class OappGoogleAuthService {

    private Application application;

    private GoogleApiClient googleApiClient;

    @Inject
    public OappGoogleAuthService(Application application) {
        this.application = application;
        this.googleApiClient = createGoogleApiClient();
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public OptionalPendingResult<GoogleSignInResult> silentSignInStatus(){
        return Auth.GoogleSignInApi.silentSignIn(googleApiClient);
    }

    public GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        return Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
    }

    public Intent signIn() {
        return Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
    }

    private GoogleSignInOptions createGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
    }

    private GoogleApiClient.OnConnectionFailedListener createGoogleApiClientOnConnectionFailedListener(){
        return new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {
                System.out.println("onConnectionFailed ====>: " + connectionResult.getErrorMessage());
            }
        };
    }

    private GoogleApiClient.ConnectionCallbacks createGoogleApiClientConnectionCallbacks() {
        return new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(Bundle bundle) {
                System.out.println("onConnected ====>: ");
            }

            @Override
            public void onConnectionSuspended(int i) {
                System.out.println("onConnectionSuspended ====>: " + i);
            }
        };
    }

    private GoogleApiClient createGoogleApiClient() {
        return new GoogleApiClient
                .Builder(this.application, createGoogleApiClientConnectionCallbacks(), createGoogleApiClientOnConnectionFailedListener())
                .addApi(Auth.GOOGLE_SIGN_IN_API, createGoogleSignInOptions())
                .build();
    }

}
