package com.example.spring_pawn_app.dto;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.model.Status;

import javax.persistence.*;
import java.time.LocalDate;

public class ContractDto {

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    private Integer id;
    private String contractCode;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Customer customer;
    private double interest;
    private Product product;
    private boolean isFlag;
    private Employee employee;
    private Status status;


    public ContractDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
