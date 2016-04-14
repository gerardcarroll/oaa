package com.overstock.android.prototype.provider;

import android.net.Uri;

import com.overstock.android.prototype.service.ProductService;

/**
 * @author itowey Created on 22/03/16.
 */
public class OappProviderContract {

    public static final String CONTENT_AUTHORITY = "com.overstock.android.prototype";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PRODUCTS_PATH = "products";

    public static final class ProductEntry {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PRODUCTS_PATH).build();

        public static final String BEST_SELLERS = "bestsellers";

        public static final String NEW_ARRIVALS = "New+Arrivals";


        public static Uri buildProductBestsellerUri() {
            return CONTENT_URI.buildUpon().appendPath(BEST_SELLERS)
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS, "nfl")
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT, "15").build();
        }

        public static Uri buildProductNewArrivalsUri() {
            return CONTENT_URI.buildUpon().appendPath(NEW_ARRIVALS)
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS, "football jersey")
                    .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT, "15").build();
        }

    }
}
