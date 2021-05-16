package com.example.betterworld.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PaymentMethod implements Serializable {
    final String paymentMethodId;
    final String cardNumber;
    final String cardType;
    final String cardYear;
    final String cardMonth;
    final String cardCVV;
    final String cardFirstName;
    final String cardLastName;

    public PaymentMethod(String paymentMethodId, String cardNumber, String cardType, String cardYear, String cardMonth, String cardCVV, String cardFirstName, String cardLastName) {
        this.paymentMethodId = paymentMethodId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardYear = cardYear;
        this.cardMonth = cardMonth;
        this.cardCVV = cardCVV;
        this.cardFirstName = cardFirstName;
        this.cardLastName = cardLastName;
    }

    public Map<String, Object> toMap(
    ) {
        Map<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("paymentMethodId", paymentMethodId);
        paymentMap.put("cardNumber", cardNumber);
        paymentMap.put("cardType", cardType);
        paymentMap.put("cardYear", cardYear);
        paymentMap.put("cardMonth", cardMonth);
        paymentMap.put("cardCVV", cardCVV);
        paymentMap.put("cardFirstName", cardFirstName);
        paymentMap.put("cardLastName", cardLastName);

        return paymentMap;
    }


    public static PaymentMethod fromMap(Map<String, Object> paymentMap) {
        String paymentMethodId = (String) paymentMap.get("paymentMethodId");
        String cardNumber = (String) paymentMap.get("cardNumber");
        String cardType = (String) paymentMap.get("cardType");
        String cardYear = (String) paymentMap.get("cardYear");
        String cardMonth = (String) paymentMap.get("cardMonth");
        String cardCVV = (String) paymentMap.get("cardCVV");
        String cardFirstName = (String) paymentMap.get("cardFirstName");
        String cardLastName = (String) paymentMap.get("cardLastName");

        return  new PaymentMethod(paymentMethodId,cardNumber,cardType,cardYear,cardMonth,cardCVV,cardFirstName,cardLastName);
    }

    public String getPaymentMethodId() { return paymentMethodId; }

    public String getCardNumber() { return cardNumber; }

    public String getCardType() { return cardType; }

    public String getCardYear() { return cardYear; }

    public String getCardMonth() { return cardMonth; }

    public String getCardCVV() { return cardCVV; }

    public String getCardFirstName() { return cardFirstName; }

    public String getCardLastName() { return cardLastName; }
}
