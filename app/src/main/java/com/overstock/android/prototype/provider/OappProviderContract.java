package com.overstock.android.prototype.provider;

import android.content.ContentResolver;
import android.net.Uri;

import com.overstock.android.prototype.interfaces.ProductService;

import javax.inject.Inject;

/**
 * Created by itowey on 22/03/16.
 */
public class OappProviderContract {

    private ProductService productService;

    @Inject
    public OappProviderContract(final ProductService productService) {
        this.productService = productService;
    }

    public static final String CONTENT_AUTHORITY = "com.overstock.android.prototype";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PRODUCTS_PATH = "products";
    public static final String CATEGORIES_PATH = "categories";
    public static final String FEED_PATH = "feed";


    public static final class ProductEntry {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PRODUCTS_PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PRODUCTS_PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PRODUCTS_PATH;

        public static final String BESTSELLERS = "bestsellers";
        public static final String NEWARRIVALS = "newarrivals";

        public static Uri buildProductBestsellerUri(){
            return CONTENT_URI.buildUpon().appendPath(BESTSELLERS)
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS, "nfl")
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT,"30")
                    .build();
        }

        public static Uri buildProductNewArrivalsUri(){
            return CONTENT_URI.buildUpon().appendPath(NEWARRIVALS)
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS,"football jersey")
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT,"30")
                    .build();
        }
    }

    public static final class CategoryEntry {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(CATEGORIES_PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + CATEGORIES_PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + CATEGORIES_PATH;

        public static final String COMMUNITIES_PATH = "communities";
    }

    public static final class FeedEntry {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(FEED_PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + FEED_PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + FEED_PATH;

        public static final String FEED_STREAM_PATH = "?";
    }
}
