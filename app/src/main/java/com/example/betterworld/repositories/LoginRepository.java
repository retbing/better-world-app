package com.example.betterworld.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class LoginRepository {

    private FirebaseAuth auth;

    @Inject
    LoginRepository(FirebaseAuth auth) {
        this.auth = auth;
    }

    public MutableLiveData<DataOrException<User, Exception>> firebaseSignInWithEmailAndPassword(String email ,String password) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task-> {
                    DataOrException<User, Exception> dataOrException = new DataOrException<>();
                    if (task.isSuccessful()) {
//                            boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser();
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            dataOrException.data = new User(firebaseUser.getEmail());
                            logErrorMessage("Login successful");
                        }
                    } else {
                        logErrorMessage("Login Unsuccessful");
                        dataOrException.exception = task.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

}