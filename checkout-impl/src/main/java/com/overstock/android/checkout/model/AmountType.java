package com.overstock.android.checkout.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public interface AmountType {

  String CREDIT = "CREDIT";
  String DISCOUNT = "DISCOUNT";
  String CHARGE = "CHARGE";

  @StringDef(
      {
          CREDIT,
          DISCOUNT,
          CHARGE
      }
  )
  @Retention(SOURCE)
  @Target(
      {
          FIELD,
          METHOD
      }
  )
  @interface AmountTypeRef {

  }
}
