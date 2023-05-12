package com.example.spring_pawn_app.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(columnDefinition = "bit(1)")
    private boolean isFlag;

    public Product() {
    }

    public Product(Integer id, String name, double price, Category category, boolean isFlag) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isFlag = isFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
