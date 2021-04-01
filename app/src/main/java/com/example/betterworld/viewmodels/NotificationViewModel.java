package com.example.betterworld.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.example.betterworld.repositories.NotificationRepository;

import java.util.List;

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
}
