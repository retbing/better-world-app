package com.example.betterworld.repositories;


import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.example.betterworld.utils.HelperClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.CHARITIES_REF;
import static com.example.betterworld.utils.Constants.CHARITIES_STORAGE_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@ActivityScoped
public class CharityRepository {

    private CollectionReference charityCollection;
    private StorageReference charityStorage;

    @Inject
    public CharityRepository(@Named(CHARITIES_REF) CollectionReference charitiesRef, @Named(CHARITIES_STORAGE_REF) StorageReference charityStorage) {
        this.charityCollection = charitiesRef;
        this.charityStorage = charityStorage;
    }

    public MutableLiveData<DataOrException<String, Exception>> uploadImageToFirebaseStorage(Uri uri) {
        MutableLiveData<DataOrException<String, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        final String fileName = HelperClass.createUniqueImageName(uri);
        charityStorage.child(fileName).putFile(uri).addOnCompleteListener(uploadTask -> {
            DataOrException<String, Exception> dataOrException = new DataOrException<>();
            if (uploadTask.isSuccessful()) {
                dataOrException.data = fileName;
            } else {
                dataOrException.exception = uploadTask.getException();
            }

            dataOrExceptionMutableLiveData.setValue(dataOrException);
        });
        return dataOrExceptionMutableLiveData;
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
