package com.example.spring_pawn_app.dto.customer;

import javax.validation.constraints.*;

public class CustomerRegisterDTO {
    @NotBlank(message = "Không được để trống tên của bạn")
    private String nameCustomer;
    @NotBlank(message = "Nhập email của bạn")
    @Email
    private String emailCustomer;
    @NotBlank(message = "Hay nhap so điện thoại của bạn")
    @Size(min = 10,max = 12)
    private String phoneCustomer;
    @NotBlank(message = "Nhap dia chi cua ban")
    private String address;
    private String note;

    public CustomerRegisterDTO() {
    }

    public CustomerRegisterDTO(String nameCustomer, String emailCustomer, String phoneCustomer, String address, String note) {
        this.nameCustomer = nameCustomer;
        this.emailCustomer = emailCustomer;
        this.phoneCustomer = phoneCustomer;
        this.address = address;
        this.note = note;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
