package com.example.betterworld.viewmodels;

import android.net.Uri;
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

    private static final String TAG = "CharityViewModel";

    @Inject
    public CharityViewModel(CharityRepository charityRepository, AuthRepository authRepository) {
        this._charityRepository = charityRepository;
        this._authRepository = authRepository;
    }

    public MutableLiveData<DataOrException<Charity, Exception>> createCharity(String title, String whoBenefits, String description, float target, Date startDate, Date dueDate, String imageName) {
        if (_authRepository.getUser() != null) {

            final String username = _authRepository.getUser().getUsername();
            final String userId = _authRepository.getUser().getUserId();


            Charity charity = new Charity(UUID.randomUUID().toString(), title,
                    description, whoBenefits, imageName, target, 0, startDate.getTime(), dueDate.getTime(),
                    "health-12345", "Health", userId, username
            );

            return _charityRepository.createCharityOnFireStore(charity);

        } else {
            Log.d(TAG, "createCharity: " + "logout the user!! user is null");
            return null;
        }
    }

    public MutableLiveData<DataOrException<String, Exception>> uploadImage(Uri uri) {
        return _charityRepository.uploadImageToFirebaseStorage(uri);
    }
}
