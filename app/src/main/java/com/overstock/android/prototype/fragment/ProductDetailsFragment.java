package com.overstock.android.prototype.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.view.ProductDetailView;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ProductDetailsFragment A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link ProductDetailsFragment.OnFragmentInteractionListener} interface to handle interaction events. Use the
 * {@link ProductDetailsFragment#newInstance} factory method to create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment implements ProductDetailView {

    public static final String TAG = ProductDetailsFragment.class.getName();

    public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

    public Product getProduct() {
        return product;
    }

    private Product product;

    private OnFragmentInteractionListener mListener;

    @Inject
    ProductDetailPresenter presenter;

    @Bind(R.id.btn_buy)
    FloatingActionButton btn_buy;

    @Inject
    Picasso picasso;

//    @Bind(R.id.product_detail_content)
//    WebView productDescription;

    @Bind(R.id.product_detail_toolbar)
    Toolbar toolbar;

    @Bind(R.id.product_details_nested_scroll_view)
    NestedScrollView nestedScrollView;

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

        nestedScrollView.setFillViewport(true);

        presenter.setView(this);
        presenter.retrieveProductDetails(product.getId());
        return view;
    }

    @Override
    public void addProductDetailsSummaryFragment(ProductDetail productDetail) {
        this.getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.prod_details_placeholder, ProductDetailsSummaryFragment.newInstance(productDetail))
                .commit();
    }

    @Override
    public void addImageGalleryFragment() {
        this.getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.image_gallery_fragment_container, ImageGalleryFragment.newInstance(product), String.valueOf(R.id.prod_details_placeholder))
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

//        try {
//            mListener = (OnFragmentInteractionListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
//        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
