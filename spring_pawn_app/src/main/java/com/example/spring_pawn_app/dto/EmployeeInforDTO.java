package com.example.spring_pawn_app.dto;

import java.time.LocalDate;

public class EmployeeInforDTO {
    private String id;
    private String name;
    private String dateOfBirth;
    private String phone;
    private String email;
    private String gender;
    private String address;
    private String id_cart;
    private String avt;
    private String userName;
    private String password;
    private String confirmPassword;

    public EmployeeInforDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public EmployeeInforDTO(String id, String name, String dateOfBirth, String phone, String email, String gender, String address, String id_cart, String avt, String userName, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.id_cart = id_cart;
        this.avt = avt;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
