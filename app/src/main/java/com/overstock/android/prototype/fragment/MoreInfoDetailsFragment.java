package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.model.ProductDetail;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rconnolly on 4/27/2016.
 */
public class MoreInfoDetailsFragment extends Fragment {

    public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

    private ProductDetail productDetail;

    @Bind(R.id.more_info_product_detail_content)
    WebView productDescription;

    public static MoreInfoDetailsFragment newInstance(ProductDetail productDetail) {
        MoreInfoDetailsFragment fragment = new MoreInfoDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_DETAILS_PARCEL, productDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more_info_details, container, false);

        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            productDetail = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }

        return view;
    }
}
