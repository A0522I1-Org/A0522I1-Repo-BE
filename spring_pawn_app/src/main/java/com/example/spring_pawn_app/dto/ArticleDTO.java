package com.example.spring_pawn_app.dto;

import com.example.spring_pawn_app.model.Employee;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

public class ArticleDTO {

    private Integer id;
    private String title;
    private String img;
    private String content;
    private LocalDate date_public;
    private Boolean isFeature;
    private Boolean isFlag;
    private Employee employee;

    public ArticleDTO() {
    }

    public ArticleDTO(Integer id, String title, String img, String content, LocalDate date_public, Boolean isFeature, Boolean isFlag, Employee employee) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.date_public = date_public;
        this.isFeature = isFeature;
        this.isFlag = isFlag;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate_public() {
        return date_public;
    }

    public void setDate_public(LocalDate date_public) {
        this.date_public = date_public;
    }

    public Boolean getFeature() {
        return isFeature;
    }

    public void setFeature(Boolean feature) {
        isFeature = feature;
    }

    public Boolean getFlag() {
        return isFlag;
    }

    public void setFlag(Boolean flag) {
        isFlag = flag;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
