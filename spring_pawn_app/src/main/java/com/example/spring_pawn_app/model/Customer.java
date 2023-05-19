package com.example.spring_pawn_app.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( columnDefinition = ("varchar(25)"))
    private String customerCode;
    @Column(columnDefinition = ("varchar(45)"))
    private String customerName;

    private LocalDate dateOfBirth;
    private Integer gender;
    @Column(columnDefinition = ("varchar(256)"))
    private String email;
    @Column(columnDefinition = ("varchar(200)"))
    private String address;
    @Column(columnDefinition = ("varchar(20)"))
    private String phone;
    @Column( columnDefinition = ("varchar(20)"))
    private String identityCard;
    @Column(columnDefinition = "varchar(200)")
    private String avatar;
    @Column(columnDefinition = "varchar(40)")
    private String status;
    private String note;
    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private boolean isFlag;

    public Customer() {
    }

    public Customer(Integer id, String customerCode, String name, LocalDate dateOfBirth, Integer gender, String email, String address, String phone, String identityCard, String avatar, String status, String note, boolean isFlag) {
        this.id = id;
        this.customerCode = customerCode;
        this.customerName = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.identityCard = identityCard;
        this.avatar = avatar;
        this.status = status;
        this.note = note;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
