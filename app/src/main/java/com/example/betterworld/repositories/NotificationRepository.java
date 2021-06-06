package com.example.betterworld.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.NOTIFICATIONS_COLLECTION;
import static com.example.betterworld.utils.Constants.NOTIFICATIONS_REF;
import static com.example.betterworld.utils.Constants.USERS_REF;

@ActivityScoped
public class NotificationRepository {
    private CollectionReference _notificationRef;
    private final CollectionReference _userRef;
    private final FirebaseAuth firebaseAuth;
    List<Notification> notificationList;


    private static final String TAG = "NotificationRepository";

    @Inject
    NotificationRepository(FirebaseAuth firebaseAuth, @Named(USERS_REF) CollectionReference userRef, @Named(NOTIFICATIONS_REF) CollectionReference notificationRef) {
        notificationList = new ArrayList<>();
        this.firebaseAuth = firebaseAuth;
        _userRef = userRef;
        _notificationRef = notificationRef;

    }

    public MutableLiveData<DataOrException<List<Notification>, Exception>> watchNotifications() {
        _notificationRef = _userRef.document(firebaseAuth.getUid()).collection(NOTIFICATIONS_COLLECTION);
        MutableLiveData<DataOrException<List<Notification>, Exception>> mutableLiveData = new MutableLiveData<>();
        _notificationRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                DataOrException<List<Notification>, Exception> dataOrException = new DataOrException<>();
                if (error != null) {
                    dataOrException.exception = error;
                } else {

                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED:
                                notificationList.add(Notification.fromMap(dc.getDocument().getData()));
                                break;
                            case MODIFIED:
                                notificationList.remove(dc.getOldIndex());
                                notificationList.add(dc.getNewIndex(), Notification.fromMap(dc.getDocument().getData()));
                                break;
                            case REMOVED:
                                notificationList.remove(dc.getOldIndex());
                                break;
                        }
                    }
                    dataOrException.data = notificationList;
                }

                mutableLiveData.setValue(dataOrException);
            }
        });
        return mutableLiveData;
    }


    public void createNotificationOnFireStore(String ownerUserId, Notification notification) {
        this._notificationRef = _userRef.document(ownerUserId).collection(NOTIFICATIONS_COLLECTION);
        _notificationRef
                .add(notification.toMap());


    }
}


