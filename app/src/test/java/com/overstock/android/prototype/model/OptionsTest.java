package com.overstock.android.prototype.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author LeeMeehan
 * @since Created on 14-Apr-16.
 */
public class OptionsTest {

  private static final Integer OPTION_ID = 2002;

  private static final float OPTION_PRICE = 25.50f;

  private static final Integer OPTION_QUANTITY_ALLOWED = 7;

  private static final Integer OPTION_QUANTITY_ON_HAND = 10;

  private static final String OPTION_DESCRIPTION = "TEST_OPTION_DESCRIPTION";

  private Options option;

  @Before
  public void setUp() {
    option = new Options(OPTION_ID, OPTION_DESCRIPTION, OPTION_QUANTITY_ON_HAND, OPTION_QUANTITY_ALLOWED, OPTION_PRICE);
  }

  @Test
  public void testGetters() {
    assertEquals(OPTION_ID.intValue(), option.getOptionId());
    assertEquals(OPTION_DESCRIPTION, option.getDescription());
    assertEquals(OPTION_PRICE, option.getPrice(), 0);
    assertEquals(OPTION_QUANTITY_ALLOWED.intValue(), option.getMaxQuantityAllowed());
    assertEquals(OPTION_QUANTITY_ON_HAND.intValue(), option.getQuantityOnHand());
  }
}
