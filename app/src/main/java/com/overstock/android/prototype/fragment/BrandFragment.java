package com.overstock.android.prototype.fragment;

import java.util.ArrayList;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.ProductAdapter;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.view.BrandView;

import de.hdodenhof.circleimageview.CircleImageView;

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

  @Bind(R.id.brand_scroll)
  NestedScrollView scrollView;

  @Bind(R.id.brand_collapsing_toolbar)
  CollapsingToolbarLayout collapsingToolbarLayout;

  @Bind(R.id.brand_appBar)
  AppBarLayout appBarLayout;

  @Bind(R.id.brand_toolbar)
  Toolbar toolbar;

  @Bind(R.id.brand_logo_icon)
  CircleImageView imageView;

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_brand, container, false);
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    toolbar.inflateMenu(R.menu.menu_main);

    final AppBarLayout.OnOffsetChangedListener listener = new AppBarLayout.OnOffsetChangedListener() {
      @Override
      public void onOffsetChanged(final AppBarLayout appBarLayout, final int verticalOffset) {
        if (collapsingToolbarLayout.getHeight() + verticalOffset < 2
          * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
          imageView.animate().alpha(1).rotation(-360).setDuration(600);
        }
        else {
          imageView.animate().alpha(0).rotation(360).setDuration(600);
        }
      }
    };
    appBarLayout.addOnOffsetChangedListener(listener);
    presenter.setView(this);
  }

  @Override
  public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
    inflater.inflate(R.menu.menu_main, menu);
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

    final ProductAdapter productAdapter = new ProductAdapter(this.getActivity(), getContext(), products);

    recyclerView_BestSellers.setHasFixedSize(false);
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(),
        LinearLayoutManager.HORIZONTAL, false);
    recyclerView_BestSellers.setLayoutManager(linearLayoutManager);
    recyclerView_BestSellers.setAdapter(productAdapter);
    recyclerView_BestSellers.setNestedScrollingEnabled(false);
    recyclerView_BestSellers.setItemAnimator(new DefaultItemAnimator());
  }

  @Override
  public void displayNewArrivals(final ArrayList<Product> products) {
    Log.d(TAG, "Passing new arrivals products to adapter to be displayed. List size : " + products.size());
    final ProductAdapter productAdapter = new ProductAdapter(this.getActivity(), getContext(), products);
    recyclerView_NewArrivals.setHasFixedSize(false);
    recyclerView_NewArrivals
        .setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
    recyclerView_NewArrivals.setAdapter(productAdapter);
    recyclerView_NewArrivals.setNestedScrollingEnabled(false);
    recyclerView_NewArrivals.setItemAnimator(new DefaultItemAnimator());
  }
}
