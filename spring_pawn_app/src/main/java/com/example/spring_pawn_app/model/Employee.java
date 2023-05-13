package com.example.spring_pawn_app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private LocalDate dateOfBirth;
    @Column(columnDefinition = ("varchar(10)"))
    private String phone;
    @Column(columnDefinition = ("varchar(256)"))
    private String email;
    private Integer gender;
    @Column(columnDefinition = ("varchar(255)"))
    private String address;
    @Column(columnDefinition = ("varchar(255)"))
    private String id_cart;
    @Column(columnDefinition = "bit")
    private boolean isFlag;
    private String avt;

    public Employee() {
    }

    public Employee(Integer id, String name, LocalDate dateOfBirth, String phone, String email, Integer gender, String address, String id_cart, boolean isFlag, String avt) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.id_cart = id_cart;
        this.isFlag = isFlag;
        this.avt = avt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }
}
