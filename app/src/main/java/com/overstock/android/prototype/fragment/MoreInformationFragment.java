package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.MoreInformationPresenter;
import com.overstock.android.prototype.view.MoreInformationView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rconnolly on 4/26/2016.
 */
public class MoreInformationFragment extends Fragment implements MoreInformationView {

    private static final String TAG = MoreInformationFragment.class.getName();

    public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

    private Product product;

    @Inject
    MoreInformationPresenter moreInformationPresenter;

    @Bind(R.id.btn_details)
    Button detailsBtn;

    @Bind(R.id.btn_shipping_returns)
    Button shippingReturnsBtn;

    @Bind(R.id.more_info_product_detail_content)
    WebView productDescription;

    public MoreInformationFragment(){}

    public static MoreInformationFragment newInstance(Product product) {
        MoreInformationFragment fragment = new MoreInformationFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_DETAILS_PARCEL, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent.Initializer.init(this.getActivity().getApplication()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more_information, container, false);
        if (getArguments() != null) {
            product = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }
        ButterKnife.bind(this, view);

        moreInformationPresenter.setView(this);
        moreInformationPresenter.retrieveProductDetails(product.getId());
        return view;
    }

    @Override
    public void displayDetails(ProductDetail productDetail) {
        Log.d(TAG, "Displaying Product Details." + productDetail.toString());
        productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

    @OnClick(R.id.btn_shipping_returns)
    public void shippingReturnsBtn_onClick(){

    }

    @Override
    public void displayShipping() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        moreInformationPresenter.onDestroy();
    }
}
