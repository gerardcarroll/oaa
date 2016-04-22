package com.overstock.android.prototype.fragment;

import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.CommunityActivity;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.view.SignUpWithEmailView;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailFragment extends Fragment implements SignUpWithEmailView {

    public static final String TAG = SignUpWithEmailFragment.class.getName();

    @Inject
    SignUpWithEmailPresenter signUpWithEmailPresenter;

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
        signUpWithEmailPresenter.setView(this);
        onDoneKeyPressedListener();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick() {
        getActivity().onBackPressed();
    }

    @Override
    @OnClick(R.id.btn_sign_up)
    public void OnSignUpClick() {
        validateCredentials();
    }

    private void validateCredentials() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = confirmPasswordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            displayToast(getActivity().getApplicationContext().getResources().getString(R.string.sign_up_required_fields_message));
        } else if (!password.equals(passwordConfirm)) {
            displayToast(getActivity().getApplicationContext().getResources().getString(R.string.sign_up_passwordconfirm_error_message));
        } else {
            signUpWithEmailPresenter.onSignUp(username, password);
        }
    }

    @Override
    public void navigateToCommunity() {
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                R.transition.slide_in_vertical, R.transition.slide_out_vertical);
        startActivity(new Intent(getActivity(), CommunityActivity.class), options.toBundle());
    }

    @Override
    public void showSignUpSuccess() {
        displayToast(getActivity().getApplicationContext().getResources().getString(R.string.successful_oapp_sign_up_message));
    }

    @Override
    public void showSignUpError() {
        displayToast(getActivity().getApplicationContext().getResources().getString(R.string.unsuccessful_oapp_sign_up_message));
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
        signUpWithEmailPresenter.onDestroy();
    }

    /**
     * This listens to when the Done key is pressed when the user is on the password text box. If pressed the onSignIn
     * method is called in the presenter
     */
    private void onDoneKeyPressedListener() {
        confirmPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validateCredentials();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

}
