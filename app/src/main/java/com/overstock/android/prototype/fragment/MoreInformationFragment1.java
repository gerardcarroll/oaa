package com.overstock.android.prototype.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.MoreInformationPresenter;
import com.overstock.android.prototype.view.MoreInformationView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rconnolly on 4/26/2016.
 */
public class MoreInformationFragment1 extends Fragment implements MoreInformationView {

    private static final String TAG = MoreInformationFragment1.class.getName();

    public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

    private ProductDetail productDetail;

    private MoreInfoPagerAdapter moreInfoPagerAdapter;

    @Inject
    MoreInformationPresenter moreInformationPresenter;

    @Bind(R.id.viewpager_more_info)
    ViewPager viewPager;

    public MoreInformationFragment1(){}

    public static MoreInformationFragment1 newInstance(ProductDetail productDetail) {
        MoreInformationFragment1 fragment = new MoreInformationFragment1();
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

        final View view = inflater.inflate(R.layout.fragment_more_information1, container, false);
        if (getArguments() != null) {
            productDetail = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }

        ButterKnife.bind(this, view);

        // Set the ViewPager adapter
        WizardPagerAdapter adapter = new WizardPagerAdapter();
        viewPager.setAdapter(adapter);


        final View shippingReturnsView = inflater.inflate(R.layout.fragment_more_info_shipping_returns, viewPager, false);
        shippingReturnsView.setTag("Shipping/Returns");
        final View detailsView = inflater.inflate(R.layout.fragment_more_info_details, viewPager, false);
        detailsView.setTag("Detailed Info");
//
        setShippingViewChildViews(shippingReturnsView, productDetail);
        setDetailsViewChildViews(detailsView, productDetail);
//
//        moreInfoPagerAdapter = new MoreInfoPagerAdapter(this.getContext(), viewPager);
//
        viewPager.setCurrentItem(0);

//       moreInformationPresenter.setView(this);
//       moreInformationPresenter.retrieveProductDetails(productDetail.getId());
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
        detailsViewHolder.detailsTitle.setText(getString(R.string.details_title_text));
        detailsViewHolder.productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

    @Override
    public void displayDetails(ProductDetail productDetail) {
        Log.d(TAG, "Displaying Product Details." + productDetail.toString());
        //productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        moreInformationPresenter.onDestroy();
    }
}

class MoreInfoPagerAdapter extends PagerAdapter  {

    private ArrayList<View> views = new ArrayList<View>();

    private ViewPager viewPager;

    private Context context;

    public MoreInfoPagerAdapter(Context context, ViewPager viewPager) {
        this.context = context;
        this.viewPager = viewPager;
        this.viewPager.setAdapter(this);
        this.views = views;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition (Object object)
    {
        int index = views.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = String.valueOf(views.get(position).getTag());
        return pageTitle;
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position)
    {
        View v = views.get(position);
        container.addView(v, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.notifyDataSetChanged();
        return v;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object)
    {
        container.removeView(views.get(position));
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


    public int addView (View v, int position)
    {
        views.add(position, v);
        notifyDataSetChanged();
        return position;
    }
}

class DetailsViewHolder {

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

class WizardPagerAdapter extends PagerAdapter {

    public WizardPagerAdapter() {
    }

    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = 0;
        switch (position) {
            case 0:
                resId = R.layout.fragment_more_info_shipping_returns;
                break;
            case 1:
                resId = R.layout.fragment_more_info_details;
                break;
        }
        View view = inflater.inflate(resId, null);
        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((LinearLayout) arg2);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((LinearLayout) arg1);
    }
}

