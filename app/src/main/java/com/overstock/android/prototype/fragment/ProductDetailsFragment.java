package com.overstock.android.prototype.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.view.ProductDetailView;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ProductDetailsFragment
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment implements ProductDetailView {

    public static final String TAG = ProductDetailsFragment.class.getName();

    public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

    private Product product;

    private OnFragmentInteractionListener mListener;

    @Inject
    ProductDetailPresenter presenter;

    @Inject
    Picasso picasso;

    @Bind(R.id.product_detail_product_name)
    TextView productName;

    @Bind(R.id.product_detail_product_price)
    TextView productPrice;

    @Bind(R.id.product_detail_content)
    WebView productDescription;

    @Bind(R.id.product_detail_toolbar)
    Toolbar toolbar;

    @Bind(R.id.btn_buy)
    FloatingActionButton btn_buy;

    public ProductDetailsFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param product
     * @return A new instance of fragment ProductDetailsFragment.
     */
    public static ProductDetailsFragment newInstance(Product product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_DETAILS_PARCEL, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Dagger inject dependencies
        ApplicationComponent.Initializer.init(this.getActivity().getApplication()).inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        presenter.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        if (getArguments() != null) {
            product = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
        }

        ButterKnife.bind(this, view);

        // Add image gallery
        addImageGalleryFragment();

        productName.setText(product.getName());
        final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
        productPrice.setText(this.getString(R.string.product_price_fmt, currencyCode, String.valueOf(product.getMemberPrice())));

        presenter.setView(this);
        presenter.retrieveProductDetails(product.getId());
        return view;
    }

    private void addImageGalleryFragment() {
        this.getChildFragmentManager()
                .beginTransaction()
                .add(R.id.image_gallery_fragment_container, ImageGalleryFragment.newInstance(product))
                .commit();
    }

    @OnClick(R.id.btn_buy)
    public void expandBottom_sheet() {
        ProductDetail productDetails = presenter.getProductDetails();
        if (productDetails != null) {
            ProductBottomSheetFragment productBottomSheetFragment = new ProductBottomSheetFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("productDetails", Parcels.wrap(productDetails));
            productBottomSheetFragment.setArguments(bundle);
            productBottomSheetFragment.show(getFragmentManager(), productBottomSheetFragment.getTag());
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void displayProductDetails(final ProductDetail productDetail) {
        Log.d(TAG, "Displaying Product Details." + productDetail.toString());
        productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
        btn_buy.setVisibility(View.VISIBLE);
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
