package com.overstock.android.prototype.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.ProductDetailMoreInfoAdapter;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.MoreInformationPresenter;
import com.overstock.android.prototype.view.MoreInformationView;

import java.util.ArrayList;
import java.util.List;

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

    private ProductDetailMoreInfoAdapter moreInfoAdapter;

    @Inject
    MoreInformationPresenter moreInformationPresenter;

    @Bind(R.id.btn_details)
    Button detailsBtn;

    @Bind(R.id.btn_shipping_returns)
    Button shippingReturnsBtn;

    @Bind(R.id.viewpager_more_info)
    ViewPager viewPager;

    public MoreInformationFragment(){}

    public static MoreInformationFragment newInstance(ProductDetail productDetail) {
        MoreInformationFragment fragment = new MoreInformationFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_DETAILS_PARCEL, productDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent.Initializer.init(this.getActivity().getApplication()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_more_information, container, false);
        if (getArguments() != null) {
            productDetail = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }

        ButterKnife.bind(this, view);

        List<View> pagerViews = new ArrayList<>();

        final View detailsView = inflater.inflate(R.layout.fragment_more_info_details, viewPager, false);
        detailsView.setTag("Details");
        final View shippingReturnsView = inflater.inflate(R.layout.fragment_more_info_shipping_returns, viewPager, false);
        shippingReturnsView.setTag("Shipping/Returns");

        setDetailsViewChildViews(detailsView, productDetail);
        setShippingViewChildViews(shippingReturnsView, productDetail);

        pagerViews.add(detailsView);
        pagerViews.add(shippingReturnsView);

        moreInfoAdapter = new ProductDetailMoreInfoAdapter(pagerViews);
        viewPager.setAdapter(moreInfoAdapter);
        viewPager.setCurrentItem(0);

        addViewPagerButtonNavigation();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {}

            @Override
            public void onPageSelected(final int position) {
                updateViewPagerButtonStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(final int state) {}
        });

        moreInformationPresenter.setView(this);
        return view;
    }

    private void setShippingViewChildViews(View shippingReturnsView, ProductDetail productDetail){
        ShippingReturnsViewHolder shippingReturnsViewHolder = new ShippingReturnsViewHolder(shippingReturnsView);
        shippingReturnsViewHolder.shippingTitle.setText(getString(R.string.shipping_title_text));
        shippingReturnsViewHolder.shippingText.setText(getString(R.string.lorem_ipsum_text));
        shippingReturnsViewHolder.returnsTitle.setText(getString(R.string.returns_title_text));
        shippingReturnsViewHolder.returnsText.setText(getString(R.string.lorem_ipsum_text));
    }

    private void setDetailsViewChildViews(View detailsView, ProductDetail productDetail){
        DetailsViewHolder detailsViewHolder = new DetailsViewHolder(detailsView);
        detailsViewHolder.productName.setText(productDetail.getName());
        detailsViewHolder.detailsTitle.setText(getString(R.string.details_title_text));
        detailsViewHolder.productDescription.setBackgroundColor(Color.TRANSPARENT);
        detailsViewHolder.productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

    private void addViewPagerButtonNavigation(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == detailsBtn){
                    viewPager.setCurrentItem(0, true);
                    updateViewPagerButtonStyle(viewPager.getCurrentItem());
                } else {
                    viewPager.setCurrentItem(1, true);
                    updateViewPagerButtonStyle(viewPager.getCurrentItem());
                }
            }
        };
        detailsBtn.setOnClickListener(listener);
        shippingReturnsBtn.setOnClickListener(listener);
    }

    private void updateViewPagerButtonStyle(int currentPosition){
        if (currentPosition == 0){
            detailsBtn.setBackground(getResources().getDrawable(R.drawable.details));
            detailsBtn.setTextColor(getResources().getColor(R.color.details_text_color));
            shippingReturnsBtn.setBackground(getResources().getDrawable(R.drawable.shipping));
            shippingReturnsBtn.setTextColor(getResources().getColor(R.color.shipping_text_color));
        } else {
            shippingReturnsBtn.setBackground(getResources().getDrawable(R.drawable.details));
            shippingReturnsBtn.setTextColor(getResources().getColor(R.color.details_text_color));
            detailsBtn.setBackground(getResources().getDrawable(R.drawable.shipping));
            detailsBtn.setTextColor(getResources().getColor(R.color.shipping_text_color));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        moreInformationPresenter.onDestroy();
    }
}

class DetailsViewHolder {

    @Bind(R.id.details_product_name)
    TextView productName;

    @Bind(R.id.details_title)
    TextView detailsTitle;

    @Bind(R.id.more_info_product_detail_content)
    WebView productDescription;

    DetailsViewHolder(View v){
        ButterKnife.bind(this, v);
    }
}

class ShippingReturnsViewHolder {

    @Bind(R.id.shipping_title)
    TextView shippingTitle;

    @Bind(R.id.shipping_lorem_ipsum_text)
    TextView shippingText;

    @Bind(R.id.returns_title)
    TextView returnsTitle;

    @Bind(R.id.returns_lorem_ipsum_text)
    TextView returnsText;

    ShippingReturnsViewHolder(View v){
        ButterKnife.bind(this, v);
    }
}

