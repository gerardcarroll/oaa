package com.overstock.android.checkout.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CheckoutAmountWithTypeTypeAdapterFactory implements TypeAdapterFactory {
  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    if (!CheckoutAmountWithType.class.isAssignableFrom(type.getRawType())) {
      return null;
    }
    return (TypeAdapter<T>) new Adapter(gson);
  }

  public static final class Adapter extends TypeAdapter<CheckoutAmountWithType> {

    private final TypeAdapter<CheckoutAmount> amountAdapter;

    public Adapter(Gson gson) {
      amountAdapter = gson.getAdapter(CheckoutAmount.class);
    }

    @Override
    public void write(JsonWriter out, CheckoutAmountWithType value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }
      boolean hasParent = false;
      try {
        out.beginObject();
      }
      catch (IllegalStateException e) {
        hasParent = true;
      }
      out.name(value.type());
      amountAdapter.write(out, value.amount());
      if (!hasParent) {
        out.endObject();
      }
    }

    @Override
    public CheckoutAmountWithType read(JsonReader in) throws IOException {
      JsonToken peek = in.peek();
      if (peek == JsonToken.NULL) {
        in.nextNull();
        return null;
      }
      CheckoutAmountWithType amount = null;
      if (in.hasNext()) {
        String key;
        CheckoutAmount checkoutAmount;
        JsonToken peek1 = in.peek();
        if (peek1 == JsonToken.NAME) {
          key = in.nextName();
          checkoutAmount = amountAdapter.read(in);
        }
        else {
          in.beginObject();
          key = in.nextName();
          checkoutAmount = amountAdapter.read(in);
          in.endObject();
        }
        amount = new CheckoutAmountWithType(key, checkoutAmount);
      }
      return amount;
    }
  }
}
