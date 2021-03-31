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
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_COLLECTION;
import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class LoginRepository {

    private FirebaseAuth auth;
    private CollectionReference userCollection;

    @Inject
    LoginRepository(FirebaseAuth auth, @Named(USERS_REF) CollectionReference userCollection) {
        this.auth = auth;
        this.userCollection = userCollection;
    }

    public MutableLiveData<DataOrException<User, Exception>> getUserFromFirestore(String uuid, String email) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        // Add a new document with a generated ID
        userCollection
                .document(uuid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DataOrException<User,Exception> dataOrException = new DataOrException<>();
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                       User user = User.userFromMap(uuid, email, document.getData());
                       dataOrException.data = user;
                    } else {
                        dataOrException.exception = task.getException();
                    }
                } else {
                    dataOrException.exception = task.getException();
                }

                dataOrExceptionMutableLiveData.setValue(dataOrException);
            }
        });

        return dataOrExceptionMutableLiveData;
    }

    public MutableLiveData<DataOrException<User, Exception>> firebaseSignInWithEmailAndPassword(String email, String password) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    DataOrException<User, Exception> dataOrException = new DataOrException<>();
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            final MutableLiveData<DataOrException<User, Exception>> userFirestoreOrExceptionMutable = getUserFromFirestore(firebaseUser.getUid(), email);
                            dataOrException = (DataOrException<User, Exception>) userFirestoreOrExceptionMutable.getValue();
                        }
                    } else {
                        dataOrException.exception = task.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

}