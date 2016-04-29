package com.overstock.android.prototype.component;

import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.fragment.SignInWithEmailFragment;
import com.overstock.android.prototype.fragment.SignUpWithEmailFragment;

/**
 * Created by itowey on 11/03/16.
 */
public interface HomeActivityInject {
  void inject(HomeActivity homeActivity);

  void inject(HomeFragment homeFragment);

  void inject(SignInWithEmailFragment signInWithEmailFragment);

  void inject(SignUpWithEmailFragment signUpWithEmailFragment);
}
