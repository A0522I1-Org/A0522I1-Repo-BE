package com.example.spring_pawn_app.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(columnDefinition = ("varchar(45)"))
    private String userName;
    @NotNull
    @Column(columnDefinition = ("varchar(255)"))
    private String password;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private boolean isFlag;

    public User() {
    }

    public User(Integer id, String userName, String password, Employee employee, boolean isFlag) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.employee = employee;
        this.isFlag = isFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
