package com.example.betterworld.repositories;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.utils.HelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import static com.example.betterworld.utils.Constants.PROFILES_STORAGE_REF;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

public class ProfileRepository {
    private FirebaseAuth _firebaseAuth;
    private StorageReference profilessStorage;
    @Inject
    public ProfileRepository(FirebaseAuth firebaseAuth, @Named(PROFILES_STORAGE_REF) StorageReference charityStorag) {
        this.profilessStorage = charityStorag;
        this._firebaseAuth = firebaseAuth;

    }

    public FirebaseUser checkIfUserIsAuthenticatedInFirebase() {
        return _firebaseAuth.getCurrentUser();
    }

    public MutableLiveData<DataOrException<String, Exception>> uploadImageToFirebaseStorage(Uri uri) {
        MutableLiveData<DataOrException<String, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        String fileName;
        if (uri == null) {
            fileName = Charity.DEFAULT_IMAGE;
        } else {
            fileName = HelperClass.createUniqueImageName(uri);
        }
        profilessStorage.child(fileName).putFile(uri).addOnCompleteListener(uploadTask -> {
            DataOrException<String, Exception> dataOrException = new DataOrException<>();
            if (uploadTask.isSuccessful()) {
                profilessStorage.child(fileName).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Uri> task) {
                        DataOrException<String, Exception> dataOrException = new DataOrException<>();

                        if(task.isSuccessful()) {
                            dataOrException.data = task.getResult().toString();
                        } else {
                            dataOrException.exception = task.getException();
                        }
                        dataOrExceptionMutableLiveData.setValue(dataOrException);
                    }
                });
            } else {
                dataOrException.exception = uploadTask.getException();
            }

        });
        return dataOrExceptionMutableLiveData;
    }

}


