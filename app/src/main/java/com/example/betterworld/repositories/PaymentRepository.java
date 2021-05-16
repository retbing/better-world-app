package com.example.betterworld.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.PaymentMethod;
import com.google.firebase.firestore.CollectionReference;

import javax.inject.Named;

import dagger.hilt.android.scopes.ActivityScoped;

import static com.example.betterworld.utils.Constants.PAYMENT_METHOD_REF;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;


@ActivityScoped
public class PaymentRepository {

    private CollectionReference paymentCollection;

    public PaymentRepository(@Named(PAYMENT_METHOD_REF) CollectionReference  _paymentCollection) {
        this.paymentCollection = _paymentCollection;
    }



    public MutableLiveData<DataOrException<PaymentMethod, Exception>> createCharityOnFireStore(PaymentMethod payment) {
        MutableLiveData<DataOrException<PaymentMethod, Exception>> dataOrExceptionMutableLiveData
                = new MutableLiveData<>();
        paymentCollection
                .document(payment.getPaymentMethodId())
                .set(payment.toMap())
                .addOnCompleteListener(paymentTask -> {
                    DataOrException<PaymentMethod, Exception> dataOrException = new DataOrException<>();
                    if (paymentTask.isSuccessful()) {
                        dataOrException.data = payment;
                        logErrorMessage("Payment has been Created");
                    } else {
                        logErrorMessage("error on");
                        dataOrException.exception = paymentTask.getException();
                    }
                    dataOrExceptionMutableLiveData.setValue(dataOrException);
                });
        return dataOrExceptionMutableLiveData;
    }
}
