package com.example.betterworld.repositories;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.inject.Named;

import dagger.hilt.android.qualifiers.ActivityContext;

import static com.example.betterworld.utils.Actions.goToLoginActivity;
import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@Singleton
public class RegisterRepository {
    private FirebaseAuth auth;
    private CollectionReference usersRef;

    @Inject
    RegisterRepository(
            FirebaseAuth auth, @Named(USERS_REF) CollectionReference usersRef
    ) {
        this.auth = auth;
        this.usersRef = usersRef;
    }

    public void getUser(FirebaseUser firebaseUser) {

    }

    public MutableLiveData<DataOrException<User, Exception>> createUserInFirestore(String email, String password,String username) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                logErrorMessage("User Have been Created ");
                 FirebaseUser user = auth.getCurrentUser();
//                Toast.makeText(this.context, "User Have been Created ", Toast.LENGTH_LONG).show();

            }
            else {
//                Toast.makeText(this.context, "User creation Unsuccessful", Toast.LENGTH_LONG).show();
                logErrorMessage("User creation Unsuccessful");
            }
        });
        return dataOrExceptionMutableLiveData;
    }
}
