package com.example.betterworld.validatorRules.register;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.BR;
import com.example.betterworld.R;

public class RegisterValidator extends BaseObservable {
//    private RegisterFields fields = new RegisterFields();
//    private RegisterErrorFields errors = new RegisterErrorFields();
//    private MutableLiveData<RegisterFields> buttonClick = new MutableLiveData<>();
//
//    @Bindable
//    public boolean isValid() {
//        boolean valid = isEmailValid(false) && isPasswordValid(false);
//        valid = isPasswordValid(false) && valid;
//        notifyPropertyChanged(BR.emailError);
//        notifyPropertyChanged(BR.passwordError);
//        return valid;
//    }
//
//    public boolean isEmailValid(boolean setMessage) {
//        // Minimum a@b.c
//        String email = fields.getEmail();
//        if (email != null && email.length() > 5) {
//            int indexOfAt = email.indexOf("@");
//            int indexOfDot = email.lastIndexOf(".");
//            if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email.length() - 1) {
//                errors.setEmail(null);
//                notifyPropertyChanged(BR.valid);
//                return true;
//            } else {
//                if (setMessage) {
//                    errors.setEmail(R.string.error_format_invalid);
//                    notifyPropertyChanged(BR.valid);
//                }
//                return false;
//            }
//        }
//        if (setMessage) {
//            errors.setEmail(R.string.error_too_short);
//            notifyPropertyChanged(BR.valid);
//        }
//
//        return false;
//    }
//
//    public boolean isPasswordValid(boolean setMessage) {
//        String password = fields.getPassword();
//        if (password != null && password.length() > 6) {
//            errors.setPassword(null);
//            notifyPropertyChanged(BR.valid);
//            return true;
//        } else {
//            if (setMessage) {
//                errors.setPassword(R.string.error_too_short);
//                notifyPropertyChanged(BR.valid);
//            }
//
//            return false;
//        }
//    }
//
//    public void onClick() {
//        if (isValid()) {
//            buttonClick.setValue(fields);
//        }
//    }
//
//    public MutableLiveData<RegisterFields> getLoginFields() {
//        return buttonClick;
//    }
//
//    public RegisterFields getFields() {
//        return fields;
//    }
//
//    @Bindable
//    public Integer getEmailError() {
//        return errors.getEmail();
//    }
//
//    @Bindable
//    public Integer getPasswordError() {
//        return errors.getPassword();
//    }
}
