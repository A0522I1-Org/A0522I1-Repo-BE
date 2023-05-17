package com.example.spring_pawn_app.dto;

import java.time.LocalDate;


public class CustomerDto {
    private Integer id;
    private String customerCode;
    private String name;
    private Integer gender;
    private String address;
    private String phone;
    private String identityCard;
    private boolean isFlag;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String customerCode, String name, Integer gender, String address, String phone, String identityCard, boolean isFlag) {
        this.id = id;
        this.customerCode = customerCode;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.identityCard = identityCard;
        this.isFlag = isFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }


    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
