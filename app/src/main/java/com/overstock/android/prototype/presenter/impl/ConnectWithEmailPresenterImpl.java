package com.overstock.android.prototype.presenter.impl;

import com.overstock.android.prototype.presenter.ConnectWithEmailPresenter;
import com.overstock.android.prototype.service.ConnectWithEmailService;
import com.overstock.android.prototype.view.ConnectWithEmailView;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class ConnectWithEmailPresenterImpl implements ConnectWithEmailPresenter {

    private ConnectWithEmailView connectWithEmailView;
    private ConnectWithEmailService connectWithEmailService;

    public ConnectWithEmailPresenterImpl(ConnectWithEmailView view, ConnectWithEmailService service){
        this.connectWithEmailView = view;
        this.connectWithEmailService = service;
    }

    @Override
    public void setView(ConnectWithEmailView connectWithEmailView) {

    }

    @Override
    public void onDestroy() {

    }
}
