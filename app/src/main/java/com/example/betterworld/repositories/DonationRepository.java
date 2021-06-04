package com.example.betterworld.repositories;


import android.os.Bundle;
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

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.DONATION_REF;
import static com.example.betterworld.utils.Constants.TAG;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;
import com.google.firebase.messaging.RemoteMessage;

@ActivityScoped
public class DonationRepository {

     CollectionReference donationCollection;
    FirebaseMessaging firebaseMessaging;


    @Inject
    public DonationRepository(@Named(DONATION_REF) CollectionReference charitiesRef,FirebaseMessaging  firebaseMessaging) {
        this.donationCollection = charitiesRef;
        this.firebaseMessaging = firebaseMessaging;

    }




    public MutableLiveData<DataOrException<Donation, Exception>> createCharityOnFireStore(Donation donation) {
        MutableLiveData<DataOrException<Donation, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        donationCollection
                .document(donation.getDonationId())
                .set(donation.toMap())
                .addOnCompleteListener(donationTask -> {
                    DataOrException<Donation, Exception> dataOrException = new DataOrException<>();
                    if (donationTask.isSuccessful()) {
                        dataOrException.data = donation;
                        logErrorMessage("Charity has been Created");
                    } else {
                        logErrorMessage("error on");
                        dataOrException.exception = donationTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }
    public void sendDonationNotification(){
        firebaseMessaging.getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
// The topic name can be optionally prefixed with "/topics/".
                        String topic = "donation";


                        Bundle b = new Bundle();
                        b.putString("title","Loza Is here");
                        b.putString("message","Loza Is here");
// Send a message to the devices subscribed to the provided topic.
                        firebaseMessaging.send(new RemoteMessage(b));
                        // Log and toast
                        String msg = token.toString();
                        Log.d(TAG,"Token: "+msg);

                    }
                });
    }


//    public MutableLiveData<DataOrException<Charity, Exception>> getCharityByIDFromFireStore(String charityID) {
//        MutableLiveData<DataOrException<Charity, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
//        charityCollection
//                .document(charityID)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        DataOrException<Charity,Exception> dataOrException = new DataOrException<>();
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                Charity charity = Charity.fromMap(document.getData());
//                                dataOrException.data = charity;
//                            } else {
//                                dataOrException.exception = task.getException();
//                            }
//                        } else {
//                            dataOrException.exception = task.getException();
//                            logErrorMessage(task.getException().getMessage());
//                        }
//
//                        dataOrExceptionMutableLiveData.setValue(dataOrException);
//                    }
//                });
//        return dataOrExceptionMutableLiveData;
//    }
//
//    public MutableLiveData<DataOrException<List<Charity>, Exception>> watchCharitiesByCategory(String categoryId) {
//        MutableLiveData<DataOrException<List<Charity>, Exception>> mutableLiveData = new MutableLiveData<>();
//        charityCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                DataOrException<List<Charity>, Exception> dataOrException = new DataOrException<>();
//                if (error != null) {
//                    dataOrException.exception = error;
//                } else {
//
//                    for (DocumentChange dc : value.getDocumentChanges()) {
//                        switch (dc.getType()) {
//                            case ADDED:
//                                charityList.add(Charity.fromMap(dc.getDocument().getData()));
//                                break;
//                            case MODIFIED:
//                                charityList.remove(dc.getOldIndex());
//                                charityList.add(dc.getNewIndex(), Charity.fromMap(dc.getDocument().getData()));
//                                break;
//                            case REMOVED:
//                                charityList.remove(dc.getOldIndex());
//                                break;
//                        }
//                    }
//                    List<Charity> filteredList = new ArrayList<>();
//
//                    for(Charity charity : charityList) {
//                        if(charity.getCategoryId().contains(categoryId)) {
//                            filteredList.add(charity);
//                        }
//                    }
//
//                    dataOrException.data = filteredList;
//                }
//
//                mutableLiveData.setValue(dataOrException);
//            }
//        });
//        return mutableLiveData;
//    }
}
