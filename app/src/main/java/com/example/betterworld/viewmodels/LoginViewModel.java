package com.example.betterworld.viewmodels;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.VisibleForTesting;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.validatorRules.login.LoginFields;
import com.example.betterworld.validatorRules.login.LoginForm;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.google.firebase.auth.AuthCredential;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    public LiveData<DataOrException<User, Exception>> authenticatedUserLiveData;
    private AuthRepository authRepository;
    private LoginForm login;
    private View.OnFocusChangeListener onFocusEmail;
    private View.OnFocusChangeListener onFocusPassword;

    @Inject
    LoginViewModel(AuthRepository loginRepository) {
        this.authRepository = loginRepository;
    }

    void signInWithGoogle(AuthCredential googleAuthCredential) {
//        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

    public void signInWithEmailAndPassword(String email, String password) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithEmailAndPassword( email, password);
    }

    public void signOutUser() {
        authRepository.signOutAuthUser();
    }

    @VisibleForTesting
    public void init() {
        login = new LoginForm();
        onFocusEmail =  new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    login.isEmailValid(true);
                }
            }
        };

        onFocusPassword = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    login.isPasswordValid(true);
                }
            }
        };
    }

    public LoginForm getLogin() {
        return login;
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return onFocusEmail;
    }

    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return onFocusPassword;
    }

    public void onButtonClick() {
        login.onClick();
    }

    public MutableLiveData<LoginFields> getLoginFields() {
        return login.getLoginFields();
    }


    @BindingAdapter("error")
    public static void setError(EditText editText, Object strOrResId) {
        if (strOrResId instanceof Integer) {
            editText.setError(
                    editText.getContext().getString((Integer) strOrResId));
        } else {
            editText.setError((String) strOrResId);
        }
    }

    @BindingAdapter("onFocus")
    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }


}
