package com.example.spring_pawn_app.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title",columnDefinition = "varchar(45)")
    private String title;
    @Column(name = "img")
    private String img;

    @Column(name = "content",columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "date_public")
    private LocalDate date_public;

    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isFeature;
    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isFlag;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Article() {
    }


    public Article(Integer id, String title, String img, String content, LocalDate date_public, boolean isFeature, boolean isFlag, Employee employee) {
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

    public boolean isFeature() {
        return isFeature;
    }

    public void setFeature(boolean feature) {
        isFeature = feature;
    }

    public LocalDate getDate_public() {
        return date_public;
    }

    public void setDate_public(LocalDate date_public) {
        this.date_public = date_public;
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
}
