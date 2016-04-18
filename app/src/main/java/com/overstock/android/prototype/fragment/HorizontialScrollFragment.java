package com.overstock.android.prototype.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.ProductAdapter;
import com.overstock.android.prototype.model.Product;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A generic fragment with textview / recyclerview view objects
 *
 * @author itowey
 * @version 1.0
 * @since 2016-04-07
 *
 */
public class HorizontialScrollFragment extends Fragment {

    private static final String PARCELABLES = "PARCELABLES";
    private static final String DISPLAY_TEXT = "DISPLAY_TEXT";
    public static final String TAG = HorizontialScrollFragment.class.getName();


    @Bind(R.id.rv_horizontal_scroll_frag)
    RecyclerView rvHorizontalScrollFrag;

    @Bind(R.id.tv_horizontal_scroll_frag)
    TextView tvHorizontalScrollFrag;

    // fragment parameters
    private ArrayList<Product> products;
    private String displayText;
    private ProductAdapter productAdapter;
    private OnFragmentInteractionListener mListener;

    public HorizontialScrollFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param parcelables Parameter 1.
     * @return A new instance of fragment HorizontialScrollFragment.
     */
    public static HorizontialScrollFragment newInstance(ArrayList<Product> parcelables, String labelText) {
        HorizontialScrollFragment fragment = new HorizontialScrollFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(PARCELABLES, parcelables);
        args.putString(DISPLAY_TEXT, labelText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            products = getArguments().getParcelableArrayList(PARCELABLES);
            displayText = getArguments().getString(DISPLAY_TEXT);
        }
        productAdapter = new ProductAdapter(this.getActivity(), getContext(), products);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horizontial_scroll, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        tvHorizontalScrollFrag.setText(displayText);
        rvHorizontalScrollFrag.setHasFixedSize(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvHorizontalScrollFrag.setLayoutManager(linearLayoutManager);
        rvHorizontalScrollFrag.setAdapter(productAdapter);
        rvHorizontalScrollFrag.setNestedScrollingEnabled(false);
        rvHorizontalScrollFrag.setItemAnimator(new DefaultItemAnimator());
        tvHorizontalScrollFrag.setVisibility(View.VISIBLE);
        rvHorizontalScrollFrag.setVisibility(View.VISIBLE);
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
