package com.example.betterworld.repositories;


import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.example.betterworld.models.User;
import com.example.betterworld.utils.HelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.CHARITIES_REF;
import static com.example.betterworld.utils.Constants.CHARITIES_STORAGE_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;


@ActivityScoped
public class CharityRepository {

    private CollectionReference charityCollection;
    private StorageReference charityStorage;

    List<Charity> charityList;


    @Inject
    public CharityRepository(@Named(CHARITIES_REF) CollectionReference charitiesRef, @Named(CHARITIES_STORAGE_REF) StorageReference charityStorage) {
        this.charityCollection = charitiesRef;
        this.charityStorage = charityStorage;
        charityList = new ArrayList<>();
    }

    public MutableLiveData<DataOrException<String, Exception>> uploadImageToFirebaseStorage(Uri uri) {
        MutableLiveData<DataOrException<String, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        String fileName;
        if (uri == null) {
            fileName = Charity.DEFAULT_IMAGE;
            DataOrException<String, Exception> dataOrException = new DataOrException<>();
            dataOrException.data = fileName;

            dataOrExceptionMutableLiveData.setValue(dataOrException);
        } else {
            fileName = HelperClass.createUniqueImageName(uri);

            charityStorage.child(fileName).putFile(uri).addOnCompleteListener(uploadTask -> {
                DataOrException<String, Exception> dataOrException = new DataOrException<>();
                if (uploadTask.isSuccessful()) {
                    charityStorage.child(fileName).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Uri> task) {
                            DataOrException<String, Exception> dataOrException = new DataOrException<>();

                            if (task.isSuccessful()) {
                                dataOrException.data = task.getResult().toString();
                            } else {
                                dataOrException.exception = task.getException();
                            }
                            dataOrExceptionMutableLiveData.setValue(dataOrException);
                        }
                    });
                } else {
                    dataOrException.exception = uploadTask.getException();
                }

            });
        }

        return dataOrExceptionMutableLiveData;
    }


    public MutableLiveData<DataOrException<Charity, Exception>> createCharityOnFireStore(Charity charity) {
        MutableLiveData<DataOrException<Charity, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        charityCollection
                .document(charity.getCharityId())
                .set(charity.toMap())
                .addOnCompleteListener(charityTask -> {
                    DataOrException<Charity, Exception> dataOrException = new DataOrException<>();
                    if (charityTask.isSuccessful()) {
                        dataOrException.data = charity;
                        logErrorMessage("Charity has been Created");
                    } else {
                        logErrorMessage("error on");
                        dataOrException.exception = charityTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }

    public MutableLiveData<DataOrException<Charity, Exception>> getCharityByIDFromFireStore(String charityID) {
        MutableLiveData<DataOrException<Charity, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        charityCollection
                .document(charityID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DataOrException<Charity, Exception> dataOrException = new DataOrException<>();
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Charity charity = Charity.fromMap(document.getData());
                                dataOrException.data = charity;
                            } else {
                                dataOrException.exception = task.getException();
                            }
                        } else {
                            dataOrException.exception = task.getException();
                            logErrorMessage(task.getException().getMessage());
                        }

                        dataOrExceptionMutableLiveData.setValue(dataOrException);
                    }
                });
        return dataOrExceptionMutableLiveData;
    }

    public MutableLiveData<DataOrException<List<Charity>, Exception>> watchCharitiesByCategory(String categoryId) {
        MutableLiveData<DataOrException<List<Charity>, Exception>> mutableLiveData = new MutableLiveData<>();
        charityCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                DataOrException<List<Charity>, Exception> dataOrException = new DataOrException<>();
                if (error != null) {
                    dataOrException.exception = error;
                } else {

                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED:
                                charityList.add(Charity.fromMap(dc.getDocument().getData()));
                                break;
                            case MODIFIED:
                                charityList.remove(dc.getOldIndex());
                                charityList.add(dc.getNewIndex(), Charity.fromMap(dc.getDocument().getData()));
                                break;
                            case REMOVED:
                                charityList.remove(dc.getOldIndex());
                                break;
                        }
                    }
                    List<Charity> filteredList = new ArrayList<>();

                    for (Charity charity : charityList) {
                        if (charity.getCategoryId().contains(categoryId)) {
                            filteredList.add(charity);
                        }
                    }

                    dataOrException.data = filteredList;
                }

                mutableLiveData.setValue(dataOrException);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<DataOrException<List<Charity>, Exception>> watchCharitiesByTitle(String charityTitle) {
        MutableLiveData<DataOrException<List<Charity>, Exception>> mutableLiveData = new MutableLiveData<>();

        charityCollection.get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {

                    DataOrException<List<Charity>, Exception> dataOrException = new DataOrException<>();

                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();
                            List<Charity> charities = new ArrayList<>();
                            for (DocumentSnapshot ds : documentSnapshots) {
                                Charity cd = Charity.fromMap(ds.getData());
                                if (cd.getTitle().toLowerCase().contains(charityTitle.toLowerCase())) {
                                    charities.add(cd);
                                }
                            }
                            dataOrException.data = charities;
                        } else {
                            dataOrException.exception = task.getException();
                        }

                        mutableLiveData.setValue(dataOrException);

                    }
                }
        );
        return mutableLiveData;
    }

    public MutableLiveData<DataOrException<Integer, Exception>> updateCharity(String uuid, Map<String, Object> charityMap) {

        MutableLiveData<DataOrException<Integer, Exception>> dataOrExceptionMutableLiveData = new MutableLiveData<>();
        // Add a new document with a generated ID
        charityCollection
                .document(uuid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DataOrException<Charity, Exception> dataOrException = new DataOrException<>();
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Charity charity = Charity.fromMap(document.getData());
                                if ((charityMap.get("donated")) != null) {
                                    charityMap.put("donated", ((float) charityMap.get("donated")) + charity.getDonated());
                                }
                                document.getReference().update(charityMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> updateTask) {
                                                DataOrException<Integer, Exception> dataOrException = new DataOrException<>();
                                                if (updateTask.isSuccessful()) {
                                                    dataOrException.data = 1;
                                                } else {
                                                    dataOrException.exception = updateTask.getException();
                                                }
                                                dataOrExceptionMutableLiveData.setValue(dataOrException);
                                            }
                                        });
                            } else {
                                dataOrException.exception = task.getException();
                            }
                        } else {
                            dataOrException.exception = task.getException();
                            logErrorMessage(task.getException().getMessage());
                        }
                    }
                });

        return dataOrExceptionMutableLiveData;

    }
}
