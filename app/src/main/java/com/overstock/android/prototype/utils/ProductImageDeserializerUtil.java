package com.overstock.android.prototype.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.model.ProductImages;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rconnolly on 4/14/2016.
 */
public class ProductImageDeserializerUtil implements JsonDeserializer<ProductDetail> {

//    public static void main(String[] args){
//        try {
//            String json = new Scanner(new File("c:\\users\\rconnolly\\Desktop\\android_misc\\productImagesJson.txt")).useDelimiter("\\Z").next();
//            Gson gson  = new GsonBuilder().registerTypeAdapter(ProductDetail.class, new ProductImageDeserializerUtil()).create();
//
//            ProductDetail productDetail = gson.fromJson(json,ProductDetail.class);
//            for(ProductImages s: productDetail.getProductImages()){
//                System.out.println(s.getImagePath());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public ProductDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        int id = json.getAsJsonObject().get("id").getAsInt();
        String name = json.getAsJsonObject().get("name").getAsString();
        String imageLarge = json.getAsJsonObject().get("imageLarge").getAsString();
        String image = json.getAsJsonObject().get("imageMedium1").getAsString();
        String price = json.getAsJsonObject().get("memberPrice").getAsString();
        String description = json.getAsJsonObject().get("description").getAsString();

        List<ProductImages> productImages = new ArrayList<>();
        JsonArray oViewerImages = json.getAsJsonObject().get("oViewerImages").getAsJsonArray();
        for(int i = 0; i < oViewerImages.size(); i++){
            JsonElement element = oViewerImages.get(i);
            productImages.add(new ProductImages(
                    element.getAsJsonObject().get("imageSizes").getAsJsonArray().get(2).getAsJsonObject().get("imagePath").getAsString())
            );
        }
        ProductDetail productDetail = new ProductDetail(id, name, imageLarge, image, price, 0, description, productImages);
        return productDetail;
    }
}
