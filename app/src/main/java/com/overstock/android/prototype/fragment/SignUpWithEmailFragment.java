package com.overstock.android.prototype.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.presenter.ConnectWithEmailPresenter;
import com.overstock.android.prototype.view.ConnectWithEmailView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailFragment extends Fragment implements ConnectWithEmailView {

    private ConnectWithEmailPresenter connectWithEmailPresenter;

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
        //connectWithEmailPresenter.setView(this);
    }

    @Override
    public void signUp(String username, String password, String passwordConfirm) {
        usernameEditText.setText(username);
        passwordEditText.setText(password);
        confirmPasswordEditText.setText(passwordConfirm);
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), "User was signed up successfully.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSignUpError() {
        Toast.makeText(getActivity().getApplicationContext(), "Unable to sign up user.", Toast.LENGTH_LONG).show();
    }
}
