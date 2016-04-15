package com.overstock.android.prototype.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.adapters.OptionAdapter;
import com.overstock.android.prototype.model.Options;

/**
 * @author LeeMeehan
 * @since Created on 14-Apr-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class OptionAdapterTest {

  private static final Integer OPTION_ID = 2002;

  private static final Float OPTION_PRICE = Float.valueOf("25.50");

  private static final Integer OPTION_QUANTITY_ALLOWED = 7;

  private static final Integer OPTION_QUANTITY_ON_HAND = 10;

  private static final String OPTION_DESCRIPTION = "TEST_OPTION_DESCRIPTION";

  private OptionAdapter optionAdapter;

  private ArrayList<Options> options;

  private Options optionInput;

  @Mock
  private Activity activity;

  @Mock
  private View convertView;

  @Mock
  private ViewGroup parent;

  @Mock
  private LayoutInflater inflater;

  @Mock
  private TextView textView;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    options = new ArrayList<>();
    optionInput = new Options(OPTION_ID, OPTION_DESCRIPTION, OPTION_QUANTITY_ON_HAND, OPTION_QUANTITY_ALLOWED,
        OPTION_PRICE);
    options.add(optionInput);
    optionAdapter = new OptionAdapter(options, activity);
  }

  @Test
  public void testGetItem() {
    final int position = 0;
    final Options option = (Options) optionAdapter.getItem(position);
    assertNotNull(option);
    assertEquals(option, optionInput);
  }

  @Test
  public void testGetId() {
    final int position = 0;
    assertEquals(OPTION_ID.longValue(), optionAdapter.getItemId(position));
  }

  @Test
  public void testGetCount() {
    final int expectedCount = 1;
    assertEquals(expectedCount, optionAdapter.getCount());
  }

  @Test
  public void testGetView() {
    final int position = 0;
    when(activity.getLayoutInflater()).thenReturn(inflater);
    when(inflater.inflate(anyInt(), eq(parent), eq(false))).thenReturn(textView);
    View returnedView = optionAdapter.getView(position, convertView, parent);
    assertNotNull(returnedView);
  }

}
