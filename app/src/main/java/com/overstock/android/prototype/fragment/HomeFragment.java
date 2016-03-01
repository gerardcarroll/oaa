package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.overstock.android.prototype.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author LeeMeehan
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @OnClick(R.id.googlePlus_login_btn)
    public void googlePlusLogin_onClick(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment googleSigninFragment =  new GoogleFederatedIdentityFragment();
        fragmentTransaction.add(R.id.home_activity, googleSigninFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @OnClick(R.id.facebook_login_btn)
    public void faceBookLogin_onClick(){
        Toast.makeText(getActivity(), "FaceBook Login Coming Soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.guest_login_btn)
    public void guestLogin_onClick(){
        Toast.makeText(getActivity(), "Guest Login Coming Soon!", Toast.LENGTH_LONG).show();
    }
}
