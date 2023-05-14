package com.example.spring_pawn_app.DTO;

import com.example.spring_pawn_app.model.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class ContractDTO {

    private Integer id;
    private Customer customer;
    private Status status;
    private Employee employee;
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ContractDTO(Integer id, Customer customer, Status status, Employee employee, Product product) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.employee = employee;
        this.product = product;
    }

    public ContractDTO() {
    }
}
