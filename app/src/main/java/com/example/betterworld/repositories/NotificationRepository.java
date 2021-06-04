package com.example.betterworld.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.NOTIFICATIONS_REF;
import static com.example.betterworld.utils.Constants.USERS_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@ActivityScoped
public class NotificationRepository {
    List<Notification> notificationList;
    private  CollectionReference _notificationRef;

    private static final String TAG = "NotificationRepository";

    @Inject
    NotificationRepository( @Named(NOTIFICATIONS_REF) CollectionReference _notificationRef) {
        notificationList = new ArrayList<>();
        this._notificationRef = _notificationRef;

    }

    public  int notificationSize(){
       return notificationList.size();
    }

    public MutableLiveData<DataOrException<List<Notification>, Exception>> watchNotifications() {
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

    public MutableLiveData<DataOrException<Notification, Exception>> createNotificationOnFireStore(Notification notification) {
        MutableLiveData<DataOrException<Notification, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        _notificationRef
                .document(notification.getCharityId())
                .set(notification.toMap())
                .addOnCompleteListener(notificationTask -> {
                    DataOrException<Notification, Exception> dataOrException = new DataOrException<>();
                    if (notificationTask.isSuccessful()) {
                        dataOrException.data = notification;
                        logErrorMessage("Charity has been Created");
                    } else {
                        logErrorMessage("error on");
                        dataOrException.exception = notificationTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }
}
