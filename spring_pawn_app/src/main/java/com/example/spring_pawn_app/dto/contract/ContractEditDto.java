package com.example.spring_pawn_app.dto.contract;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.model.Status;

import java.time.LocalDate;


public class ContractEditDto {
    private Integer id;
    private String contractCode;
    public ContractEditDto(Integer id, String contractCode, String customerName, Integer customerId, String productName, Integer productId, LocalDate beginDate, LocalDate endDate, Category category, Status status) {
        this.id = id;
        this.contractCode = contractCode;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.productId = productId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.category = category;
        this.status = status;
    }

    private String customerName;
    private Integer customerId;
    private String productName;
    private Integer productId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Category category;
    private Status status;

    public ContractEditDto() {
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
