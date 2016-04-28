package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

        View view = inflater.inflate(R.layout.fragment_more_information1, container, false);
        if (getArguments() != null) {
            productDetail = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }

        ButterKnife.bind(this, view);

        View shippingReturnsView = inflater.inflate(R.layout.fragment_more_info_shipping_returns, viewPager, false);
        View detailsView = inflater.inflate(R.layout.fragment_more_info_details, viewPager, false);

//        setShippingViewChildViews(shippingReturnsView, productDetail);
        setDetailsViewChildViews(detailsView, productDetail);

        moreInfoPagerAdapter = new MoreInfoPagerAdapter(viewPager);

        moreInfoPagerAdapter.addView(shippingReturnsView, 0);
        moreInfoPagerAdapter.addView(detailsView, 1);

        viewPager.setCurrentItem(0);


//        moreInformationPresenter.setView(this);
//        moreInformationPresenter.retrieveProductDetails(productDetail.getId());
        return view;
    }

    private void setShippingViewChildViews(View shippingReturnsView, ProductDetail productDetail){
        ShippingReturnsViewGroup shippingReturnsViewGroup = new ShippingReturnsViewGroup(shippingReturnsView);
        shippingReturnsViewGroup.shippingTitle.setText("TODO");
        shippingReturnsViewGroup.shippingText.setText("TODO");
        shippingReturnsViewGroup.returnsTitle.setText("TODO");
        shippingReturnsViewGroup.returnsText.setText("TODO");
    }

    private void setDetailsViewChildViews(View detailsView, ProductDetail productDetail){
        DetailsViewGroup detailsViewGroup = new DetailsViewGroup(detailsView);
        detailsViewGroup.detailsTitle.setText(productDetail.getName());
        detailsViewGroup.productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

    @Override
    public void displayDetails(ProductDetail productDetail) {
        Log.d(TAG, "Displaying Product Details." + productDetail.toString());

        //productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
    }

//    private void displayMoreInfoFragments(){
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fragment = null;
//                if(v == detailsBtn){
//                    fragment = MoreInfoDetailsFragment.newInstance(productDetail);
//                } else {
//                    fragment =  new MoreInfoShippingReturnsFragment();
//                }
//                getChildFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.more_info_container, fragment)
//                    .addToBackStack("fragment")
//                    .commit();
//            }
//        };
//        detailsBtn.setOnClickListener(listener);
//        shippingReturnsBtn.setOnClickListener(listener);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        moreInformationPresenter.onDestroy();
    }
}

class MoreInfoPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private ArrayList<View> views = new ArrayList<View>();

    private ViewPager viewPager;

    public MoreInfoPagerAdapter(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);
        this.viewPager.setAdapter(this);
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
    public Object instantiateItem (ViewGroup container, int position)
    {
        View v = views.get(position);
        container.addView(v);
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
        return view == object;
    }


    public int addView (View v, int position)
    {
        views.add (position, v);
        notifyDataSetChanged();
        return position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

class DetailsViewGroup {

    @Bind(R.id.details_title)
    TextView detailsTitle;

    @Bind(R.id.more_info_product_detail_content)
    WebView productDescription;

    DetailsViewGroup (View v){
        ButterKnife.bind(this, v);
    }
}
class ShippingReturnsViewGroup{
    @Bind(R.id.shipping_title)
    TextView shippingTitle;

    @Bind(R.id.shipping_lorem_ipsum_text)
    TextView shippingText;

    @Bind(R.id.returns_title)
    TextView returnsTitle;

    @Bind(R.id.returns_lorem_ipsum_text)
    TextView returnsText;

    ShippingReturnsViewGroup(View v){
        ButterKnife.bind(this, v);
    }


}