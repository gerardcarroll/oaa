package com.overstock.android.prototype.fragment;

import static org.mockito.Mockito.doThrow;

import com.overstock.android.prototype.rules.HomeActivityFragmentsModuleMockRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author LeeMeehan Created on 22-Mar-16.
 */
public class HomeFragmentTest {

  private HomeFragment homeFragment;

  @Rule
  public HomeActivityFragmentsModuleMockRule homeActivityMock = new HomeActivityFragmentsModuleMockRule();

  @Mock
  private GoogleFederatedIdentityFragment googleFederatedIdentityFragment;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    homeFragment = new HomeFragment();
  }

  @Test
  public void test1() {
    doThrow(new RuntimeException()).when(googleFederatedIdentityFragment).onStart();
    homeFragment.googlePlusLogin_onClick();
  }

}
