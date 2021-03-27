package com.example.betterworld.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.User;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.example.betterworld.utils.Constants.USERS_REF;

public class LoginRepository {

    private FirebaseAuth auth;
    private CollectionReference usersRef;

    @Inject
    LoginRepository(FirebaseAuth auth, @Named(USERS_REF) CollectionReference usersRef) {
        this.auth = auth;
        this.usersRef = usersRef;
    }

    public MutableLiveData<DataOrException<User, Exception>> firebaseSignInWithGoogle(AuthCredential googleAuthCredential) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        auth.signInWithCredential(googleAuthCredential).addOnCompleteListener(authTask -> {
            DataOrException<User, Exception> dataOrException = new DataOrException<>();
            if (authTask.isSuccessful()) {
                boolean isNewUser = authTask.getResult().getAdditionalUserInfo().isNewUser();
                FirebaseUser firebaseUser = auth.getCurrentUser();
                if (firebaseUser != null) {
                    User user = getUser(firebaseUser);
//                    user.isNew = isNewUser;
                    dataOrException.data = user;
                }
            } else {
                dataOrException.exception = authTask.getException();
            }
            dataOrExceptionMutableLiveData.setValue(dataOrException);
        });
        return dataOrExceptionMutableLiveData;
    }
    private User getUser(FirebaseUser firebaseUser) {
        String uid = firebaseUser.getUid();
        String name = firebaseUser.getDisplayName();
        String email = firebaseUser.getEmail();
        String photoUrl = firebaseUser.getPhotoUrl().toString();
        return new User(uid,name,photoUrl);
    }

    public MutableLiveData<DataOrException<User, Exception>> createUserInFirestore(User authenticatedUser) {
        MutableLiveData<DataOrException<User, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        DocumentReference uidRef = usersRef.document(authenticatedUser.getUuid());
        uidRef.set(authenticatedUser).addOnCompleteListener(userCreationTask -> {
            DataOrException<User, Exception> dataOrException = new DataOrException<>();
            if (userCreationTask.isSuccessful()) {
                dataOrException.data = authenticatedUser;
            } else {
                dataOrException.exception = userCreationTask.getException();
            }
            dataOrExceptionMutableLiveData.setValue(dataOrException);
        });
        return dataOrExceptionMutableLiveData;
    }
}