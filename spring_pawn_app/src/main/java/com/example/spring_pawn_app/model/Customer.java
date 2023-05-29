package com.example.spring_pawn_app.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Trần Thế Huy
 * @version 1
 * @implNote có thêm thuộc tính deleteTime vào db
 * @since 28/5/2023
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "customer_code", columnDefinition = ("varchar(25)"))
    private String customerCode;
    @Column(name = "customer_name", columnDefinition = "varchar(45)")
    private String name;
    private Integer gender;
    @Column(name = "date_of_birth", columnDefinition = "varchar(20)")
    private LocalDate dateOfBirth;
    @Column(name = "id_card", columnDefinition = "varchar(20)")
    private String identityCard;
    @Column(name = "phone_number", columnDefinition = "varchar(20)")
    private String phone;
    @Column(columnDefinition = ("varchar(256)"))
    private String email;
    @Column(columnDefinition = ("varchar(200)"))
    private String address;
    @Column(columnDefinition = "varchar(200)")
    private String avatar;
    @Column(columnDefinition = "varchar(45)")
    private String status;
    @Column(columnDefinition = "varchar(200)")
    private String note;
    @Column(name = "delete_time")
    private LocalDateTime deleteTime;
    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private boolean isFlag;

    public Customer() {
    }

    public Customer(Integer id, String customerCode, String name, Integer gender, LocalDate dateOfBirth, String identityCard, String phone, String email, String address, String avatar, String status, String note, LocalDateTime deleteTime, boolean isFlag) {
        this.id = id;
        this.customerCode = customerCode;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.identityCard = identityCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
        this.status = status;
        this.note = note;
        this.deleteTime = deleteTime;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
