package com.example.betterworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.betterworld.databinding.NotificationDataBinding;
import com.example.betterworld.models.Notification;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    List<Notification> notificationList;
    NotificationDataBinding notificationDataBinding;
    public NotificationAdapter(@NonNull Context context, int resource, List<Notification> notificationList) {
        super(context, resource, notificationList);

        this.notificationList = notificationList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Notification notification = notificationList.get(position);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        notificationDataBinding = NotificationDataBinding.inflate(inflater);
        notificationDataBinding.setNotification(notification);

        return notificationDataBinding.getRoot();
    }
}
