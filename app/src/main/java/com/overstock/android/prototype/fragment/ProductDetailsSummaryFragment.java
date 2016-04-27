package com.overstock.android.prototype.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overstock.android.prototype.R;
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

    @Bind(R.id.txt_more_information_link)
    TextView txtMoreInformationLink;

    private OnFragmentInteractionListener mListener;

    @Inject
    ProductDetailsSummaryPresenter presenter;

    public ProductDetailsSummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param productDetail
     * @return A new instance of fragment ProductDetailsSummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
        productPrice.setText(this.getString(R.string.product_price_fmt, currencyCode, String.valueOf(productDetail.getMemberPrice())));
//        //TODO: move to child presenter
//        productDetailView.displayProductDetails(productDetail);
//        //End TODO
        return view;
    }

    @OnClick(R.id.txt_more_information_link)
    public void moreInformation_onClick() {
        this.getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.prod_details_placeholder, MoreInformationFragment.newInstance(productDetail))
                .addToBackStack(null)
                .commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void displayProductDetails(final ProductDetail productDetail) {
//        Log.d(TAG, "Displaying Product Details." + productDetail.toString());
//        productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
        //TODO
//        btn_buy.setVisibility(View.VISIBLE);
    }

    @Override
    public void addHorizontalRecyclerView(int layoutResourceId, ArrayList<Product> products, String displayText) {
        Log.d(TAG, "Passing " + displayText + " products to adapter to be displayed. List size : " + products.size());

        this.getChildFragmentManager()
                .beginTransaction()
                .replace(layoutResourceId, HorizontialScrollFragment.newInstance(products, displayText), displayText)
                .commit();

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
