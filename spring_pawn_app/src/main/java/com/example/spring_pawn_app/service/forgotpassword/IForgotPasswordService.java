package com.example.spring_pawn_app.service.forgotpassword;

public interface IForgotPasswordService {
    void sendOtpByEmail(String email);
    void resetPassword(String email,String otp,String newPassword);
}
