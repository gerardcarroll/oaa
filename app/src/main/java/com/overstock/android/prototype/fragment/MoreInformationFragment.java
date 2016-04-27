package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.MoreInformationPresenter;
import com.overstock.android.prototype.view.MoreInformationView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rconnolly on 4/26/2016.
 */
public class MoreInformationFragment extends Fragment implements MoreInformationView {

    private static final String TAG = MoreInformationFragment.class.getName();

    public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

    private ProductDetail productDetail;

    @Inject
    MoreInformationPresenter moreInformationPresenter;

    @Bind(R.id.btn_details)
    Button detailsBtn;

    @Bind(R.id.btn_shipping_returns)
    Button shippingReturnsBtn;

//    @Bind(R.id.more_info_product_detail_content)
//    WebView productDescription;

    public MoreInformationFragment(){}

    public static MoreInformationFragment newInstance(ProductDetail productDetail) {
        MoreInformationFragment fragment = new MoreInformationFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_DETAILS_PARCEL, productDetail);
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
            productDetail = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }
        ButterKnife.bind(this, view);

        displayMoreInfoFragments();

        moreInformationPresenter.setView(this);
        moreInformationPresenter.retrieveProductDetails(productDetail.getId());
        return view;
    }

    @Override
    public void displayDetails(ProductDetail productDetail) {
        Log.d(TAG, "Displaying Product Details." + productDetail.toString());
        //productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

    private void displayMoreInfoFragments(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;
                if(v == detailsBtn){
                    fragment = MoreInfoDetailsFragment.newInstance(productDetail);
                } else {
                    fragment =  new MoreInfoShippingReturnsFragment();
                }
                getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.more_info_container, fragment)
                    .addToBackStack("fragment")
                    .commit();
            }
        };
        detailsBtn.setOnClickListener(listener);
        shippingReturnsBtn.setOnClickListener(listener);
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
