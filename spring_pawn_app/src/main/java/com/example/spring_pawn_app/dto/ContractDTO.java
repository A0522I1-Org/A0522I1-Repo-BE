package com.example.spring_pawn_app.dto;

import com.example.spring_pawn_app.model.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class ContractDTO {
    private Integer id;

    private LocalDate beginDate;
    private LocalDate endDate;
    private Customer customer;
    private Status status;
    private double interest;
    private Employee employee;

    private Product product;

    private boolean isFlag;

    public ContractDTO() {
    }

    public ContractDTO(Contract contract){
        this.id = contract.getId();
        this.beginDate = contract.getBeginDate();
        this.endDate = contract.getEndDate();
        this.customer = contract.getCustomer();
        this.status = contract.getStatus();
        this.interest = contract.getInterest();
        this.employee = contract.getEmployee();
        this.product = contract.getProduct();
        this.isFlag = false;
    }

    public ContractDTO(Integer id, LocalDate beginDate, LocalDate endDate, Customer customer, Status status, double interest, Employee employee, Product product, boolean isFlag) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.customer = customer;
        this.status = status;
        this.interest = interest;
        this.employee = employee;
        this.product = product;
        this.isFlag = isFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
