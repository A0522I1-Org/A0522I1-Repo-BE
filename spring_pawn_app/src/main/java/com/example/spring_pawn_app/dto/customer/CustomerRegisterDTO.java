package com.example.spring_pawn_app.dto.customer;

import javax.validation.constraints.*;

public class CustomerRegisterDTO {
    @NotBlank(message = "Không được để trống tên của bạn")
    private String customerName;
    @NotBlank(message = "Nhập email của bạn")
    @Email
    private String email;
    @NotBlank(message = "Hay nhap so điện thoại của bạn")
    @Size(min = 10,max = 12)
    private String phone;
    @NotBlank(message = "Nhap dia chi cua ban")
    private String address;
    private String note;

    public CustomerRegisterDTO() {
    }

    public CustomerRegisterDTO(String customerName, String email, String phone, String address, String note) {
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.note = note;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
