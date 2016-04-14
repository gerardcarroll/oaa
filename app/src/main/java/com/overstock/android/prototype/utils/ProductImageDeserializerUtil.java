package com.overstock.android.prototype.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rconnolly on 4/14/2016.
 */
public class ProductImageDeserializerUtil implements JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<String> photos = new ArrayList<>();
        JsonArray oViewerImages = json.getAsJsonObject().get("oViewerImages").getAsJsonArray();
        for(int i = 0; i < oViewerImages.size(); i++){
            JsonElement element = oViewerImages.get(i);
            photos.add(element.getAsJsonObject().get("imageSizes").getAsJsonArray().get(2).getAsJsonObject().get("imagePath").getAsString());
        }
        return photos;
    }
}
