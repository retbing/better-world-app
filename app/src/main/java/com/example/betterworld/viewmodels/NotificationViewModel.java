package com.example.betterworld.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.example.betterworld.repositories.NotificationRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

public class NotificationViewModel {
    private NotificationRepository _notificationRepository;
    public LiveData<DataOrException<List<Notification>, Exception>> notificationLiveData;

    @Inject
    public NotificationViewModel(NotificationRepository notificationRepository) {
        this._notificationRepository = notificationRepository;
    }

    public void watchNotifications() {
        notificationLiveData = _notificationRepository.watchNotifications();
    }
    public MutableLiveData<DataOrException<Notification, Exception>> createNotification(
             String notificationId ,
     String charityId ,
     String content
    ) {
        Notification notification = new Notification(UUID.randomUUID().toString(),charityId,content,"CHARITY",(new Date()).getTime(),false);
        return _notificationRepository.createNotificationOnFireStore(notification);
    }
}
