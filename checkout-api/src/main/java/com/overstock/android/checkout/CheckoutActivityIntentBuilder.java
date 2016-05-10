package com.overstock.android.checkout;

import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

import android.app.Activity;
import android.content.Intent;

public interface CheckoutActivityIntentBuilder {

  Intent createAndroidPayIntent(Activity activity, MaskedWallet maskedWallet);

  Intent createCheckoutIntent(Activity activity);

}
