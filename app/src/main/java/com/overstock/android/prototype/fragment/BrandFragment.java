package com.overstock.android.prototype.fragment;

import java.util.ArrayList;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.ProductAdapter;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.models.Product;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.view.BrandView;

/**
 * @author LeeMeehan Created on 07-03-2016
 */
public class BrandFragment extends Fragment implements BrandView {
  private static final String TAG = BrandFragment.class.getName();

  @Inject
  BrandPresenter presenter;

  @Bind(R.id.best_sellers)
  RecyclerView recyclerView_BestSellers;

  @Bind(R.id.new_arrivals)
  RecyclerView recyclerView_NewArrivals;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_brand, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    presenter.setView(this);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    presenter.onDestroy();
  }

  @Override
  public void displayBestSellers(final ArrayList<Product> products) {
    Log.d(TAG, "Passing best selling products to adapter to be displayed. List size : " + products.size());
    ProductAdapter productAdapter = new ProductAdapter(getContext(), products);
    recyclerView_BestSellers.setHasFixedSize(true);
    recyclerView_BestSellers
        .setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
    recyclerView_BestSellers.setAdapter(productAdapter);
    recyclerView_BestSellers.stopNestedScroll();
    recyclerView_BestSellers.setItemAnimator(new DefaultItemAnimator());
  }

  @Override
  public void displayNewArrivals(ArrayList<Product> products) {
    Log.d(TAG, "Passing new arrivals products to adapter to be displayed. List size : " + products.size());
    ProductAdapter productAdapter = new ProductAdapter(getContext(), products);
    recyclerView_NewArrivals.setHasFixedSize(true);
    recyclerView_NewArrivals
        .setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
    recyclerView_NewArrivals.setAdapter(productAdapter);
    recyclerView_NewArrivals.stopNestedScroll();
    recyclerView_NewArrivals.setItemAnimator(new DefaultItemAnimator());
  }
}
