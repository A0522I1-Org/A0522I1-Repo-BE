package com.example.spring_pawn_app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(45)")
    private String title;
    private String img;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private LocalDate date_public;

    @Column(columnDefinition = "bit(1)")
    private boolean isFlag;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Article() {
    }

    public Article(Integer id, String title, String img, String content, LocalDate date_public, boolean isFlag, Employee employee) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.date_public = date_public;
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
