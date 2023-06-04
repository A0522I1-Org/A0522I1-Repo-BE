package com.example.spring_pawn_app.dto.contract;

import com.example.spring_pawn_app.model.*;



public class ContractProductDTO {

    private Integer id;
    private String contractCode;
    private Customer customer;
    private Status status;
    private Employee employee;
    private Product product;

    public ContractProductDTO() {
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

    public ContractProductDTO(Integer id, String contractCode, Customer customer, Status status, Employee employee, Product product) {
        this.id = id;
        this.contractCode = contractCode;
        this.customer = customer;
        this.status = status;
        this.employee = employee;
        this.product = product;
    }
}
