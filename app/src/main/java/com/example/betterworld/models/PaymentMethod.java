package com.example.betterworld.models;

public class PaymentMethod {
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

    public String getPaymentMethodId() { return paymentMethodId; }

    public String getCardNumber() { return cardNumber; }

    public String getCardType() { return cardType; }

    public String getCardYear() { return cardYear; }

    public String getCardMonth() { return cardMonth; }

    public String getCardCVV() { return cardCVV; }

    public String getCardFirstName() { return cardFirstName; }

    public String getCardLastName() { return cardLastName; }
}
