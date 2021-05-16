package com.example.betterworld.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.PaymentMethod;
import com.example.betterworld.repositories.AuthRepository;
import com.example.betterworld.repositories.PaymentRepository;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

public class PaymentViewModel {

    private PaymentRepository _paymentRepository;


    @Inject
    public PaymentViewModel(PaymentRepository _paymentRepository ) {
        this._paymentRepository =_paymentRepository ;
    }
    public MutableLiveData<DataOrException<PaymentMethod, Exception>> createPayment(
              String paymentMethodId,
            String cardNumber,
            String cardType,
            String cardYear,
            String cardMonth,
            String cardCVV,
            String cardFirstName,
             String cardLastName
    ) {

        PaymentMethod payment =  new PaymentMethod(paymentMethodId,cardNumber,cardType,cardYear,cardMonth,cardCVV,cardFirstName,cardLastName);


        return _paymentRepository.createPaymentOnFireStore(payment);

    }

}
