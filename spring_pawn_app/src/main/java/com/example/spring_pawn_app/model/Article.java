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
// main
    @Column(columnDefinition = "varchar(50)")
    private String title;

    @Column(columnDefinition = "varchar(200)")
    private String img;

    @Column(name = "content",columnDefinition = "LONGTEXT")
    private String content;

    private LocalDate publicDate;

    @ColumnDefault("0")
    @Column(columnDefinition = "bit")
    private boolean isFeature;

    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private boolean isFlag;



    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Article() {
    }

    public Article(Integer id, String title, String img, String content, LocalDate publicDate, boolean isFeature, boolean isFlag, Employee employee) {

        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.publicDate = publicDate;
        this.isFlag = isFlag;
        this.isFeature = isFeature;
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

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setIsFlag(boolean flag) {
        isFlag = flag;
    }


    public Boolean getFeature() {
        return isFeature;
    }

    public void setIsFeature(Boolean feature) {
        isFeature = feature;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



}


