package com.overstock.android.prototype.module;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.module.scope.PerActivity;

import dagger.Provides;

/**
 * Created by itowey on 11/03/16.
 */
public class GoogleApiClientMockModule{

    private GoogleApiClient.OnConnectionFailedListener mConnectionFailedListener;
    private GoogleApiClient.ConnectionCallbacks mConnectionCallbacks;
    private Activity mHomeActivity;

    public GoogleApiClientMockModule(
            HomeActivity homeActivity,
            GoogleApiClient.OnConnectionFailedListener connectionFailedListener,
            GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        mConnectionFailedListener = connectionFailedListener;
        mConnectionCallbacks = connectionCallbacks;
        mHomeActivity =  homeActivity;
    }

    @PerActivity
    @Provides
    public GoogleSignInOptions provideGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
    }

    @PerActivity
    @Provides
    public GoogleApiClient providesGoogleApiClient(GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient
                .Builder(mHomeActivity)
                .enableAutoManage((FragmentActivity)mHomeActivity, mConnectionFailedListener)
                .addConnectionCallbacks(mConnectionCallbacks)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

}