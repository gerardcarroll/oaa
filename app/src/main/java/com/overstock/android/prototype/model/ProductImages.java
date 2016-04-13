package com.overstock.android.prototype.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rconnolly on 4/13/2016.
 */
public class ProductImages {

    List<String> productImages = new ArrayList<>();

    private String imageLarge;

    private String imageMedium1;

    private String imageMedium2;

    private String imageMedium3;

    public String getImageLarge() {
        return imageLarge;
    }

    public String getImageMedium1() {
        return imageMedium1;
    }

    public String getImageMedium2() {
        return imageMedium2;
    }

    public String getImageMedium3() {
        return imageMedium3;
    }

    public List<String> getProductImages() {

        productImages.add(getImageMedium1());
        productImages.add(getImageMedium2());
        productImages.add(getImageMedium3());
        productImages.add(getImageLarge());

        return productImages;
    }
}
