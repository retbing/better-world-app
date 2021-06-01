package com.example.betterworld.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.SetOptions;


import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.TAG;
import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@Singleton
public class AuthRepository {

    private FirebaseAuth auth;
    private CollectionReference userCollection;
    User user;

    @Inject
    AuthRepository(FirebaseAuth auth, @Named(USERS_REF) CollectionReference userCollection) {
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
                       user = User.userFromMap(uuid, email, document.getData());
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

    public MutableLiveData<DataOrException<User, Exception>> getUserFromFirestoreByID(String uuid) {
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
                        user = User.FromMap(document.getData());
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

    public MutableLiveData<DataOrException<User, Exception>>
    firebaseSignInWithEmailAndPassword(String email, String password) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        logErrorMessage("Email "+email +"  password: "+password);
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
                                            user = User.userFromMap(auth.getCurrentUser().getUid(), auth.getCurrentUser().getEmail(), document.getData());
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

    public MutableLiveData<DataOrException<Integer,Exception>> resetPassword(String emailAddress){
        MutableLiveData<DataOrException<Integer, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(resetTask -> {
                    DataOrException<Integer, Exception> dataOrException = new DataOrException<>();
                    if (resetTask.isSuccessful()) {
                        dataOrException.data = 1;
                    }else{
                        dataOrException.exception = resetTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

    public MutableLiveData<DataOrException<Integer,Exception>> updateAuthUser(String uuid, Map<String, Object> userMap){

        MutableLiveData<DataOrException<Integer, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        // Add a new document with a generated ID
        userCollection
                .document(uuid)
                .set(userMap, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        DataOrException<Integer, Exception> dataOrException = new DataOrException<>();
                        if (task.isSuccessful()) {
                            dataOrException.data = 1;
                            if((String) userMap.get("email") != null) {
                                Log.d(TAG, "Updating email :!"+(String) userMap.get("email"));
                                auth.getCurrentUser().updateEmail((String) userMap.get("email"))
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull  Task<Void> emailUpdateTask) {
                                                if (emailUpdateTask.isSuccessful()) {
                                                    Log.d(TAG, "User email address updated.");
                                                } else {
                                                    Log.d(TAG, "Email Not Updated !");
                                                }
                                            }
                                        });
                            }
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }else{
                            dataOrException.exception = task.getException();
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                        dataOrExceptionMutableLiveData.setValue(dataOrException);
                    }
                });

        return dataOrExceptionMutableLiveData;

    }

    public void signOut() {
        auth.signOut();
    }

    public User getUser() {
        return user;
    }


}