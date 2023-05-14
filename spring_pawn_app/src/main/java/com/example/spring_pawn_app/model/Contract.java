package com.example.spring_pawn_app.model;

<<<<<<< HEAD
import com.example.spring_pawn_app.dto.ContractDTO;
=======
import org.hibernate.annotations.ColumnDefault;
>>>>>>> 3cbc625c24b3f3ebd6fa65d053651537827c5209

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate beginDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(columnDefinition = "double DEFAULT 0.0")
    private Double interest;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(columnDefinition = "bit")
    @ColumnDefault("1")
    private boolean isFlag;

    public Contract() {
    }

<<<<<<< HEAD
    public Contract(ContractDTO contractDTO){
        this.id = contractDTO.getId();
        this.beginDate = contractDTO.getBeginDate();
        this.endDate = contractDTO.getEndDate();
        this.customer = contractDTO.getCustomer();
        this.status = contractDTO.getStatus();
        this.interest = contractDTO.getInterest();
        this.employee = contractDTO.getEmployee();
        this.product = contractDTO.getProduct();
        this.isFlag = false;
    }

    public Contract(Integer id, LocalDate beginDate, LocalDate endDate, Customer customer, Status status, double interest, Employee employee, Product product, boolean isFlag) {
=======
    public Contract(Integer id, LocalDate beginDate, LocalDate endDate, Customer customer, Status status, Double interest, Employee employee, Product product, boolean isFlag) {
>>>>>>> 3cbc625c24b3f3ebd6fa65d053651537827c5209
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

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
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
