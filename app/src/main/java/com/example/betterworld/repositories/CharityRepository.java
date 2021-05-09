package com.example.betterworld.repositories;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import javax.inject.Inject;
import javax.inject.Named;

import static com.example.betterworld.utils.Constants.CHARITIES_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class CharityRepository {

    private CollectionReference charityCollection;

    @Inject
    public CharityRepository(@Named(CHARITIES_REF) CollectionReference charitiesRef) {
        this.charityCollection = charitiesRef;
    }


    public MutableLiveData<DataOrException<Charity, Exception>> createCharityOnFireStore(Charity charity) {
        MutableLiveData<DataOrException<Charity, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        charityCollection
                .document(charity.getCharityId())
                .set(charity.toMap())
                .addOnCompleteListener(charityTask -> {
                    DataOrException<Charity, Exception> dataOrException = new DataOrException<>();
                    if (charityTask.isSuccessful()) {
                        dataOrException.data = charity;
                        logErrorMessage("Charity has been Created");
                    } else {
                        logErrorMessage("error on");
                        dataOrException.exception = charityTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }
}
