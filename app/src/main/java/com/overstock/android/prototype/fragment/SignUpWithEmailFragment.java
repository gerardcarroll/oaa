package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.presenter.ConnectWithEmailPresenter;
import com.overstock.android.prototype.view.ConnectWithEmailView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailFragment extends Fragment implements ConnectWithEmailView {

    public static final String TAG = SignUpWithEmailFragment.class.getName();

    @Inject
    ConnectWithEmailPresenter connectWithEmailPresenter;

    @Bind(R.id.et_username)
    EditText usernameEditText;

    @Bind(R.id.et_password)
    EditText passwordEditText;

    @Bind(R.id.et_confirm_password)
    EditText confirmPasswordEditText;

    @Bind(R.id.btn_cancel)
    Button cancelButton;

    @Bind(R.id.btn_sign_up)
    Button signUpButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_with_email, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        connectWithEmailPresenter.setView(this);
    }

    @Override
    public void showSignUpSuccess() {
        displayToast("New user was signed up successfully.");
    }

    @Override
    public void showSignUpError() {
        displayToast("Unable to Sign Up new user.");
    }

    @Override
    public void displayToast(String toastMessage) {
        final Toast toast = Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        connectWithEmailPresenter.onDestroy();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick(){
        getActivity().onBackPressed();
    }



    @Override
    @OnClick(R.id.btn_sign_up)
    public void OnSignUpClick(){

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        connectWithEmailPresenter.onSignUp(username, password);
    }

}