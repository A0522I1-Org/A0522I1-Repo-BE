package com.example.spring_pawn_app.model;

import javax.persistence.*;

@Entity
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(200)")
    private String img;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Img() {
    }

    public Img(String img, Product product) {
        this.img = img;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
