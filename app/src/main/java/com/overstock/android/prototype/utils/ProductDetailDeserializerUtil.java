package com.overstock.android.prototype.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.model.ProductImages;

/**
 * @author RayConnolly on 4/14/2016.
 */
public class ProductDetailDeserializerUtil implements JsonDeserializer<ProductDetail> {

  @Override
  public ProductDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {

    int id = json.getAsJsonObject().get("id").getAsInt();
    String name = json.getAsJsonObject().get("name").getAsString();
    String imageLarge = json.getAsJsonObject().get("imageLarge").getAsString();
    String image = json.getAsJsonObject().get("imageMedium1").getAsString();
    String price = json.getAsJsonObject().get("memberPrice").getAsString();
    String description = json.getAsJsonObject().get("description").getAsString();

    List<Options> options = new ArrayList<>();
    JsonArray optionsJson = json.getAsJsonObject().get("options").getAsJsonArray();
    for (int i = 0; i < optionsJson.size(); i++) {
      JsonElement element = optionsJson.get(i);
      options.add(new Options(element.getAsJsonObject().get("optionId").getAsInt(),
          element.getAsJsonObject().get("decription").getAsString(),
          element.getAsJsonObject().get("qtyOnHand").getAsInt(),
          element.getAsJsonObject().get("maxQuantityAllowed").getAsInt(),
          element.getAsJsonObject().get("price").getAsFloat()));
    }

    List<ProductImages> productImages = new ArrayList<>();
    JsonArray oViewerImages = json.getAsJsonObject().get("oViewerImages").getAsJsonArray();
    for (int i = 0; i < oViewerImages.size(); i++) {
      JsonElement element = oViewerImages.get(i);
      productImages.add(new ProductImages(element.getAsJsonObject().get("imageSizes").getAsJsonArray().get(2)
          .getAsJsonObject().get("imagePath").getAsString()));
    }
    ProductDetail productDetail = new ProductDetail(id, name, imageLarge, image, Float.valueOf(price), 0, description,
        options, productImages);
    return productDetail;
  }
}
