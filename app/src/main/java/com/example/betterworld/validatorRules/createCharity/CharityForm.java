package com.example.betterworld.validatorRules.createCharity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.BR;
import com.example.betterworld.R;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;


public class CharityForm extends BaseObservable {
    private CharityFields fields = new CharityFields();
    private CharityErrorFields errors = new CharityErrorFields();
    private MutableLiveData<CharityFields> buttonClick = new MutableLiveData<>();

    @Bindable
    public boolean isValid() {
        logErrorMessage("Inside isValod");
        boolean valid = isProfessionValid(false) ;
        notifyPropertyChanged(BR.professionError);
        return valid;
    }

    public boolean  isProfessionValid(boolean setMessage) {

        String password = fields.getProfession();

        logErrorMessage("Profession : "+password);
        if (password != null) {
            errors.setProfession(null);
            notifyPropertyChanged(BR.valid);
            return true;
        }
        if (setMessage) {
            errors.setProfession(R.string.error_too_short);
            notifyPropertyChanged(BR.valid);
        }

        return false;
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
    public Integer getProfessionError() {
        return errors.getProfession();
    }
}