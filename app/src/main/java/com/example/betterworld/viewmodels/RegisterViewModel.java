package com.example.betterworld.viewmodels;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.VisibleForTesting;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.RegisterRepository;
import com.example.betterworld.validatorRules.login.LoginFields;
import com.example.betterworld.validatorRules.login.LoginForm;
import com.example.betterworld.validatorRules.register.RegisterFields;
import com.example.betterworld.validatorRules.register.RegisterForm;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;

    public LiveData<DataOrException<FirebaseUser, Exception>> createdAuthUserLiveData;
    public LiveData<DataOrException<User, Exception>> createdUserLiveData;

    private RegisterForm register;
    private View.OnFocusChangeListener onFocusEmail;
    private View.OnFocusChangeListener onFocusPassword;
    private View.OnFocusChangeListener onFocusUsername;
    @Inject
    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }


    public void createUser(User user) {

        createdUserLiveData = registerRepository.createUserInFirestore(user);
    }

    public void createNewAuthUser(String email, String password) {
        createdAuthUserLiveData = registerRepository.createAuthUserInFirebase(email, password);
    }


    @VisibleForTesting
    public void init() {
        register = new RegisterForm();
        onFocusEmail =  new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    register.isEmailValid(true);
                }
            }
        };

        onFocusPassword = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    register.isPasswordValid(true);
                }
            }
        };

        onFocusUsername = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    register.isUsernameValid(true);
                }
            }
        };
    }

    public RegisterForm getRegister() {
        return register;
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return onFocusEmail;
    }

    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return onFocusPassword;
    }
    public View.OnFocusChangeListener getUsernameOnFocusChangeListener() {
        return onFocusUsername;
    }

    public void onButtonClick() {
        register.onClick();
    }

    public MutableLiveData<RegisterFields> getLoginFields() {
        return register.getRegisterFields();
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
