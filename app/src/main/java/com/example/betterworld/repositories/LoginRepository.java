package com.example.betterworld.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;



import javax.inject.Inject;
import javax.inject.Named;

import static com.example.betterworld.utils.Constants.USERS_REF;

public class LoginRepository {

    private FirebaseAuth auth;
    private CollectionReference userCollection;

    @Inject
    LoginRepository(FirebaseAuth auth, @Named(USERS_REF) CollectionReference userCollection) {
        this.auth = auth;
        this.userCollection = userCollection;
    }
    public void signOutAuthUser() {
        auth.signOut();
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
                            userCollection
                                    .document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DataOrException<User,Exception> dataOrException = new DataOrException<>();
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            User user = User.userFromMap(auth.getCurrentUser().getUid(), auth.getCurrentUser().getEmail(), document.getData());
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
                    } else {
                        dataOrException.exception = task.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

    public void signOut() {
        auth.signOut();
    }

}