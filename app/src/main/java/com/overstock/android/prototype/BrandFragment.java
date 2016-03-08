package com.overstock.android.prototype;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.view.BrandView;

/**
 * @author LeeMeehan Created on 07-03-2016
 */
public class BrandFragment extends Fragment implements BrandView {

  @Inject
  BrandPresenter presenter;

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
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    presenter.onDestroy();
  }
}
