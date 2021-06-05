package com.example.betterworld.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Donation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;
import javax.inject.Named;

import static com.example.betterworld.utils.Constants.DONATION_REF;
import static com.example.betterworld.utils.Constants.TAG;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

public class HomeRepository {
    FirebaseMessaging firebaseMessaging;


    @Inject
    public HomeRepository() {

    }


}
