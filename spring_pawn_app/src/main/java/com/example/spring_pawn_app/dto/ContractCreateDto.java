package com.example.spring_pawn_app.dto;
import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Status;
import com.example.spring_pawn_app.validate.DateRange;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
  * Create by ThuongVTH
  * Date create: 02/06/2023
 */
@DateRange()
public class ContractCreateDto {

    @NotNull(message = "Bắt buộc")
    @Future(message = "Ngày bắt đầu không được ở quá khứ")
    private LocalDate beginDate;

    @NotNull(message = "Bắt buộc")
    private LocalDate endDate;

    @NotNull(message = "Bắt buộc")
    private Customer customer;

    @Positive(message = "Lãi xuất là số dương")
    private double interest;

    @NotNull(message = "Bắt buộc")
    private String nameProduct;

    @Positive(message = "Giá phải là số dương")
    private double price;

    @NotNull(message = "Bắt buộc")
    private Category category;

    @NotNull(message = "Bắt buộc")
    private Status status;

    private String username;
    @NotNull(message = "Bắt buộc")
    private String imgPath;
    public ContractCreateDto() {
    }

    public ContractCreateDto(LocalDate beginDate, LocalDate endDate, Customer customer, double interest, String nameProduct, double price, Category category, Status status, String username, String imgPath) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.customer = customer;
        this.interest = interest;
        this.status = status;
        this.nameProduct = nameProduct;
        this.price = price;
        this.category = category;
        this.username = username;
        this.imgPath = imgPath;
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

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
