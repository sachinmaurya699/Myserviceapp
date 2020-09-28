package com.sossolution.serviceonway.Activity;

import com.razorpay.PaymentData;

interface PaymentResultListener {

    void onPaymentSuccess(String razorpayPaymentID);

    void onPaymentSuccess(String razorpayPaymentID, PaymentData data);

    void onPaymentError(int code, String response);

}
