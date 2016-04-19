package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.view.BrandView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author LeeMeehan Created on 07-03-2016
 */
public class BrandFragment extends Fragment implements BrandView {
    private static final String TAG = BrandFragment.class.getName();

    @Inject
    BrandPresenter presenter;

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
                } else {
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
    public void onPause() {
        super.onPause();
        for(Fragment fragment : getChildFragmentManager().getFragments()){
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public void addHorizontialRecyclerView(
            final int layoutResourceId, final ArrayList<Product> products, final String displayText) {
        Log.d(TAG, "Passing " + displayText + " products to adapter to be displayed. List size : " + products.size());
        getChildFragmentManager()
                .beginTransaction()
                .add(layoutResourceId, HorizontialScrollFragment.newInstance(products, displayText))
                .commit();
    }

}
