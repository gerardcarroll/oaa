package com.overstock.android.prototype.presenter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.presenter.impl.SignInWithEmailPresenterImpl;
import com.overstock.android.prototype.service.ParseService;
import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * Created by gcarroll on 27/04/2016.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, application = com.overstock.android.prototype.TestOAppPrototypeApplication.class)
@RunWith(RobolectricGradleTestRunner.class)
public class SignInWithEmailPresenterTest {

  private static final String USERNAME = "gcarroll@overstock.com";

  private static final String PASSWORD = "1234";

  @Mock
  private ParseService parseService;

  @Mock
  private SignInWithEmailView signInWithEmailView;

  private SignInWithEmailPresenter signInWithEmailPresenter;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    signInWithEmailPresenter = new SignInWithEmailPresenterImpl(parseService);
    signInWithEmailPresenter.setView(signInWithEmailView);
  }

  @Test
  public void testValidateCredentials_GOOD() {
    signInWithEmailPresenter.validateCredentials(USERNAME, PASSWORD);
    verify(parseService).loginParseUser(USERNAME, PASSWORD, signInWithEmailView);
  }

  @Test
  public void testValidateCredentials_NO_USERNAME() {
    signInWithEmailPresenter.validateCredentials("", PASSWORD);
    verify(signInWithEmailView).displayToast(anyString());
  }

  @Test
  public void testValidateCredentials_NO_PASSWORD() throws Exception {
    signInWithEmailPresenter.validateCredentials(USERNAME, "");
    verify(signInWithEmailView).displayToast(anyString());
  }
}
