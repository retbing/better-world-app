package com.example.betterworld.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_REF;

@Singleton
public class SplashRepository {
    private FirebaseAuth _firebaseAuth;
    private CollectionReference _usersRef;

    @Inject
    public SplashRepository(FirebaseAuth firebaseAuth, @Named(USERS_REF) CollectionReference usersRef) {
        this._firebaseAuth = firebaseAuth;
        this._usersRef = usersRef;
    }

    public FirebaseUser checkIfUserIsAuthenticatedInFirebase() {
        return _firebaseAuth.getCurrentUser();
    }

    public String getFirebaseUserUid() {
        FirebaseUser firebaseUser = _firebaseAuth.getCurrentUser();
        return firebaseUser.getUid();
    }

    public MutableLiveData<DataOrException<User, Exception>> getUserDataFromFirestore(String uid) {

        MutableLiveData<DataOrException<User, Exception>> userMutableLiveData = new MutableLiveData<>();

        _usersRef.document(uid).get().addOnCompleteListener(
                userTask -> {
                    DataOrException<User, Exception> dataOrException = new DataOrException<>();
                    if (userTask.isSuccessful()) {
                        DocumentSnapshot userDoc = userTask.getResult();
                        if (userDoc.exists()) {
                            dataOrException.data = userDoc.toObject(User.class);
                        }
                    } else {
                        dataOrException.exception = userTask.getException();
                    }
                    userMutableLiveData.setValue(dataOrException);
                });
        return userMutableLiveData;
    }
}
