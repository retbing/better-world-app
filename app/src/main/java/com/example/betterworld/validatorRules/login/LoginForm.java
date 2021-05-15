package com.example.betterworld.validatorRules.login;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.BR;
import com.example.betterworld.R;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class LoginForm extends BaseObservable {
    private LoginFields fields = new LoginFields();
    private LoginErrorFields errors = new LoginErrorFields();
    private MutableLiveData<LoginFields> buttonClick = new MutableLiveData<>();

    @Bindable
    public boolean isValid() {
        boolean valid = isPasswordValid(false) ;
        valid = isEmailValid(false) && valid;
        notifyPropertyChanged(BR.passwordError);
        notifyPropertyChanged(BR.emailError);
        return valid;
    }

    public boolean isEmailValid(boolean setMessage) {
        // Minimum a@b.c
        String email = fields.getEmail();
        logErrorMessage(" email text is" + email);
        if (email != null ) {
            int indexOfAt = email.indexOf("@");
            int indexOfDot = email.lastIndexOf(".");
            if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email.length() - 1) {
                errors.setEmail(null);
                notifyPropertyChanged(BR.valid);
                return true;
            } else {
                if (setMessage) {
                    errors.setEmail(R.string.error_format_invalid);
                    notifyPropertyChanged(BR.valid);
                }
                return false;
            }
        }
        if (setMessage) {
            errors.setEmail(R.string.error_too_short);
            notifyPropertyChanged(BR.valid);
        }

        return false;
    }

    public boolean isPasswordValid(boolean setMessage) {
        String password = fields.getPassword();
        logErrorMessage("password : "+password);

        if (password != null && password.length() >= 6) {
            errors.setPassword(null);
            notifyPropertyChanged(BR.valid);
            return true;
        } else {
            if (setMessage) {
                errors.setPassword(R.string.error_too_short);
                notifyPropertyChanged(BR.valid);
            }

            return false;
        }
    }

    public void onClick() {
        if (isValid()) {
            buttonClick.setValue(fields);
        }
    }

    public MutableLiveData<LoginFields> getLoginFields() {
        return buttonClick;
    }

    public LoginFields getFields() {
        return fields;
    }

    @Bindable
    public Integer getEmailError() {
        return errors.getEmail();
    }

    @Bindable
    public Integer getPasswordError() {
        return errors.getPassword();
    }
}