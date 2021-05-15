package com.example.betterworld.viewmodels;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.VisibleForTesting;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.CharityRepository;
import com.example.betterworld.validatorRules.createCharity.CharityFields;
import com.example.betterworld.validatorRules.createCharity.CharityForm;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class CharityViewModel extends ViewModel {


    private CharityRepository _charityRepository;
    private AuthRepository _authRepository;
    private CharityForm charity;
    private View.OnFocusChangeListener onFocusProfession;


    private static final String TAG = "CharityViewModel";

    @Inject
    public CharityViewModel(CharityRepository charityRepository, AuthRepository authRepository) {
        this._charityRepository = charityRepository;
        this._authRepository = authRepository;
    }


    public MutableLiveData<DataOrException<Charity, Exception>> createCharity(String title, String whoBenefits, String description, float target, Date startDate, Date dueDate) {

        if (_authRepository.getUser() != null) {

            final String username = _authRepository.getUser().getUsername();
            final String userId = _authRepository.getUser().getUserId();

            Charity charity = new Charity(UUID.randomUUID().toString(), title,
                    description, whoBenefits, "", target, 0, startDate.getTime(), dueDate.getTime(),
                    "Health", "health-12345", userId, username
            );

            return _charityRepository.createCharityOnFireStore(charity);

        } else {
            Log.d(TAG, "createCharity: " + "logout the user!! user is null");
            return null;
        }
    }

    @VisibleForTesting
    public void init() {
        charity = new CharityForm();

        logErrorMessage("Initalizing VisibleForTesting ...");
        onFocusProfession =  new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {

                EditText et = (EditText) view;
                logErrorMessage("Initalizing onFocusChange ... "+et.getText());
                if (et.getText().length() > 0 && !focused) {

                    charity.isProfessionValid(true);
                }
            }
        };

    }

    public CharityForm getCharity() {
        return charity;
    }

    public View.OnFocusChangeListener getProfessionOnFocusChangeListener() {
        logErrorMessage("Started getProfessionOnFocusChangeListener ");
        return onFocusProfession;
    }

    public void onButtonClick() {
        charity.onClick();
    }

    public MutableLiveData<CharityFields> getCharityFields() {
        return charity.getCharityFields();
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
