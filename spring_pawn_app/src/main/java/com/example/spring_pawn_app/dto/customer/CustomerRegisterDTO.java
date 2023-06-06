package com.example.spring_pawn_app.dto.customer;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerRegisterDTO {
    /**
     * Create by: ManPD
     * Date create: 21/5/2023
     */
    @NotBlank(message = "Không được để trống tên của bạn")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ]+([ ][a-zA-ZÀ-ỹ]+)*$", message = "Tên của bạn không đúng")
    @Length(min = 5, message = "Tên bạn quá ngắn")
    @Length(max = 50, message = "Tên bạn quá dài")
    private String customerName;

    @NotBlank(message = "Nhập email của bạn")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{1,253}\\.[a-zA-Z]{2,63}$", message = "Email của bạn không đúng")
    @Length(min = 7, message = "Email quá ngắn")
    @Length(max = 254, message = "Email quá dài")
    private String email;

    @NotBlank(message = "Hay nhap so điện thoại của bạn")
    @Pattern(regexp = "^(0|\\+?84)(\\d){9}$", message = "Số điện thoại không đúng")
    @Size(min = 10, max = 12)
    private String phone;

    @NotBlank(message = "Nhap dia chi cua ban")
    @Pattern(regexp = "^[0-9a-zA-Z\\u00C0-\\u1FFF\\u2C00-\\uD7FF\\uF900-\\uFFFD\\p{Mn}\\s,/-]*$", message = "Địa chỉ không đúng")
    @Length(min = 20, message = "Tên địa chỉ quá ngắn")
    @Length(max = 50, message = "Tên địa chỉ quá dài")
    private String address;

    @NotBlank(message = "Hãy nhập CCCD của bạn")
    @Pattern(regexp = "^\\d{12}$", message = "CCCD của bạn không đúng")
    private String idCardCustomer;

    @Length(max = 500, message = "Nội dung quá dài")
    private String note;


    public CustomerRegisterDTO() {
    }

    public CustomerRegisterDTO(String customerName, String email, String phone, String address, String idCardCustomer, String note) {
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.idCardCustomer = idCardCustomer;
        this.note = note;
    }

    public String getIdCardCustomer() {
        return idCardCustomer;
    }

    public void setIdCardCustomer(String idCardCustomer) {
        this.idCardCustomer = idCardCustomer;
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