package com.overstock.android.prototype.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 *
 * @author LeeMeehan
 * @since Created on 26-Apr-16.
 */
@Parcel
public class OViewerImages {

    @SerializedName("cdnPath")
    private String originalImagePath;

    private int originalWidth;

    private int originalHeight;

    private List<ImageSizes> imageSizes;

    //Used by Gson
    public OViewerImages() {
    }

    public OViewerImages(final String originalImagePath, final int originalWidth, final int originalHeight, List<ImageSizes> imageSizes) {
        this.originalImagePath = originalImagePath;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        this.imageSizes = imageSizes;
    }

    public String getOriginalImagePath() {
        return originalImagePath;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public int getOriginalHeight() {
        return originalHeight;
    }

    public List<ImageSizes> getImageSizes() {
        return imageSizes;
    }
}
