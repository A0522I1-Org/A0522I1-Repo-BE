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
=======
    @Column(name = "title",columnDefinition = "varchar(45)")
    private String title;
    @Column(name = "img")
// TrungDV
    private String img;

    @Column(name = "content",columnDefinition = "LONGTEXT")
    private String content;

// main
    private LocalDate publicDate;

    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private boolean isFlag;
=======
    @Column(name = "date_public")
    private LocalDate date_public;

    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isFeature;
    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isFlag;
// TrungDV

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Article() {
    }

// main
    public Article(Integer id, String title, String img, String content, LocalDate publicDate, boolean isFlag, Employee employee) {
=======

    public Article(Integer id, String title, String img, String content, LocalDate date_public, boolean isFeature, boolean isFlag, Employee employee) {
>>>>> TrungDV
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
// main
        this.publicDate = publicDate;
=======
        this.date_public = date_public;
        this.isFeature = isFeature;
// TrungDV
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
      
// main
    public LocalDate getPublicDate() {
        return publicDate;
=======
    public boolean isFeature() {
        return isFeature;
    }

    public void setFeature(boolean feature) {
        isFeature = feature;
    }

    public LocalDate getDate_public() {
        return date_public;
// TrungDV
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
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
