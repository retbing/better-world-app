package com.example.betterworld.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.example.betterworld.models.User;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.NotificationRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

public class NotificationViewModel {
    private NotificationRepository _notificationRepository;
    private AuthRepository _authRepository;
    public LiveData<DataOrException<List<Notification>, Exception>> notificationLiveData;

    @Inject
    public NotificationViewModel(NotificationRepository notificationRepository, AuthRepository authRepository) {
        this._notificationRepository = notificationRepository;
        this._authRepository = authRepository;
    }

    public void watchNotifications() {
        notificationLiveData = _notificationRepository.watchNotifications();
    }

    public void createNotification(
            String charityOwnerId,
            String charityId,
            String content,
            String charityType
            ) {
        Notification notification = new Notification("", charityId, content, charityType, (new Date()).getTime(), false);
        _notificationRepository.createNotificationOnFireStore(charityOwnerId, notification);
    }

    public User getLoggedInUser() {
        return _authRepository.getUser();
    }
}
