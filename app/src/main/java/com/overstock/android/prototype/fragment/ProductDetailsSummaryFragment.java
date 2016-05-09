package com.overstock.android.prototype.fragment;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductDetailsSummaryPresenter;
import com.overstock.android.prototype.view.ProductDetailsSummaryView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductDetailsSummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductDetailsSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsSummaryFragment extends Fragment implements ProductDetailsSummaryView {

    private static final String TAG = ProductDetailsSummaryFragment.class.getName();
    private ProductDetail productDetail;

    @Bind(R.id.product_detail_product_name)
    TextView productName;

    @Bind(R.id.product_detail_product_price)
    TextView productPrice;

    @Bind(R.id.product_detail_product_review)
    TextView productReview;

    @Bind(R.id.review_star)
    ImageView reviewStar;

    @Bind(R.id.txt_more_information_link)
    TextView txtMoreInformationLink;

    private OnFragmentInteractionListener mListener;

    @Inject
    ProductDetailsSummaryPresenter presenter;

    public ProductDetailsSummaryFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param productDetail
     * @return A new instance of fragment ProductDetailsSummaryFragment.
     */
    public static ProductDetailsSummaryFragment newInstance(ProductDetail productDetail) {
        ProductDetailsSummaryFragment fragment = new ProductDetailsSummaryFragment();
        Bundle args = new Bundle();
        args.putParcelable(ProductDetailsFragment.PRODUCT_DETAILS_PARCEL, productDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent.Initializer.init((Application)this.getContext().getApplicationContext()).inject(this);
        presenter.setView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details_summary, container, false);
        if (getArguments() != null) {
            productDetail = getArguments().getParcelable(ProductDetailsFragment.PRODUCT_DETAILS_PARCEL);
        }
        ButterKnife.bind(this, view);

        productName.setText(productDetail.getName());

        final String review = productDetail.getReviews();
        if (review != null) {
            productName.getLayoutParams().width = setRelativeViewWidth(60);
            productReview.setText(" " + review + " " + getResources().getString(R.string.reviews_text));
        } else {
            productName.getLayoutParams().width = setRelativeViewWidth(100);
            productReview.setVisibility(View.INVISIBLE);
            reviewStar.setVisibility(View.INVISIBLE);
        }

        final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
        productPrice.setText(this.getString(R.string.product_price_fmt, currencyCode, String.valueOf(productDetail.getMemberPrice())));

        presenter.addSimilarItemsRecyclerView();
        return view;
    }

    @OnClick(R.id.txt_more_information_link)
    public void moreInformation_onClick() {
        this.getParentFragment().getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.prod_details_placeholder, MoreInformationFragment.newInstance(productDetail), String.valueOf(R.id.prod_details_placeholder))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void addHorizontalRecyclerView(int layoutResourceId, ArrayList<Product> products, String displayText) {
        Log.d(TAG, "Passing " + displayText + " products to adapter to be displayed. List size : " + products.size());

        this.getChildFragmentManager()
                .beginTransaction()
                .replace(layoutResourceId, HorizontialScrollFragment.newInstance(products, displayText), displayText)
                .commit();

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Method to set width of view relative to current screen width
     * @param widthVal
     * @return
     */
    private int setRelativeViewWidth(int widthVal){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        double widthPercentage = ((float) (width)) / 100.0;
        int newWidth = (int)(widthPercentage * widthVal);
        return newWidth;
    }
}
