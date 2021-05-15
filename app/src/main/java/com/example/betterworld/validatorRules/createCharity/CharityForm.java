package com.example.betterworld.validatorRules.createCharity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.BR;
import com.example.betterworld.R;
import com.example.betterworld.validatorRules.login.LoginErrorFields;
import com.example.betterworld.validatorRules.login.LoginFields;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;


public class CharityForm extends BaseObservable {
    private CharityFields fields = new CharityFields();
    private CharityErrorFields errors = new CharityErrorFields();
    private MutableLiveData<CharityFields> buttonClick = new MutableLiveData<>();

    @Bindable
    public boolean isValid() {
        boolean valid = isPasswordValid(false) ;
        notifyPropertyChanged(BR.passwordError);
        return valid;
    }

    public boolean isEmailValid(boolean setMessage) {

        String password = fields.getPassword();
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

    public boolean isPasswordValid(boolean setMessage) {
        String password = fields.getPassword();
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

    public MutableLiveData<CharityFields> getCharityFields() {
        return buttonClick;
    }

    public CharityFields getFields() {
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