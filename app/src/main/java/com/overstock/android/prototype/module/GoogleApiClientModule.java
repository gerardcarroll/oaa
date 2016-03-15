package com.overstock.android.prototype.module;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itowey on 10/03/16.
 */
@Module
public class GoogleApiClientModule {

    private Activity mHomeActivity;

    public GoogleApiClientModule(
            HomeActivity homeActivity) {
        mHomeActivity =  homeActivity;
    }

    @ActivityScope
    @Provides
    public GoogleSignInOptions provideGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
    }

    @ActivityScope
    @Provides
    public GoogleApiClient.OnConnectionFailedListener provideGoogleApiClientOnConnectionFailedListener(){
        return new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {
                System.out.println("onConnectionFailed ====>: " + connectionResult.getErrorMessage());
            }
        };
    }

    @ActivityScope
    @Provides
    public GoogleApiClient.ConnectionCallbacks provideGoogleApiClientConnectionCallbacks() {
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


    @ActivityScope
    @Provides
    public GoogleApiClient providesGoogleApiClient(GoogleSignInOptions googleSignInOptions,
                                                   GoogleApiClient.OnConnectionFailedListener googleApiClientOnConnectionFailedListener,
                                                   GoogleApiClient.ConnectionCallbacks googleApiClientConnectionCallbacks
                                                   ) {
        return new GoogleApiClient
                .Builder(mHomeActivity)
                .enableAutoManage((FragmentActivity)mHomeActivity, googleApiClientOnConnectionFailedListener)
                .addConnectionCallbacks(googleApiClientConnectionCallbacks)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

}