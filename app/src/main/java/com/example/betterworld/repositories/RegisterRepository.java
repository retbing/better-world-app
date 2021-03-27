package com.example.betterworld.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.inject.Named;


import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@Singleton
public class RegisterRepository {
    private FirebaseAuth auth;
    private CollectionReference usersRef;

    @Inject
    RegisterRepository(FirebaseAuth auth, @Named(USERS_REF) CollectionReference usersRef) {
        this.auth = auth;
        this.usersRef = usersRef;
    }

    public MutableLiveData<DataOrException<FirebaseUser, Exception>> createUserInFirestore(String email, String password,String username) {
        MutableLiveData<DataOrException<FirebaseUser, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            DataOrException<FirebaseUser, Exception> dataOrException = new DataOrException<>();
            if(task.isSuccessful()){
                logErrorMessage("User Have been Created ");
                 FirebaseUser user = auth.getCurrentUser();
                 dataOrException.data = user;
            }
            else {
                dataOrException.exception = task.getException();
                logErrorMessage("User creation Unsuccessful");
            }
        });
        return dataOrExceptionMutableLiveData;
    }
}
