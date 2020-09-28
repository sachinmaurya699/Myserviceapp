package com.sossolution.serviceonway.Interface;

public interface OtpReceivedInterface{

    void onOtpReceived(String otp);
    void onOtpTimeout();

}
