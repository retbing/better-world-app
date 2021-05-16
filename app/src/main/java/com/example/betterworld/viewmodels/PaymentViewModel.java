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
    public MutableLiveData<DataOrException<PaymentMethod, Exception>> createCharity(
            String title, String categoryId, String categoryName,
            String whoBenefits, String description,
            float target, Date startDate, Date dueDate, String imageName) {

    }

}
