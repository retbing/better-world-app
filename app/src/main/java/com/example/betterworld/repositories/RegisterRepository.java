package com.example.betterworld.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
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
    public User getUser(FirebaseUser firebaseUser) {
        String uid = firebaseUser.getUid();
        String name = firebaseUser.getDisplayName();
        String email = firebaseUser.getEmail();
        return new User(uid, name, email);
    }

    public MutableLiveData<DataOrException<User, Exception>> createUserInFirestore(String email, String password) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            DataOrException<User, Exception> dataOrException = new DataOrException<>();
            if(task.isSuccessful()){
                FirebaseUser user = auth.getCurrentUser();
                dataOrException.data = getUser(user);;
                logErrorMessage("User Have been Created ");

            }
            else {
                dataOrException.exception = task.getException();
                logErrorMessage("User creation Unsuccessful");
            }
            dataOrExceptionMutableLiveData.setValue(dataOrException);
        });
        return dataOrExceptionMutableLiveData;
    }
}
