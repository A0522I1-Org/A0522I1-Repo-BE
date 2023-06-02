package com.example.spring_pawn_app.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(45)")
    private String roleName;
    private String name;
    public Role() {
    }

    public Role(Integer id, String roleName, String name) {
        this.id = id;
        this.roleName = roleName;
        this.name = name;
    }

    public Role(Integer id, String roleName) {
        this.id = id;
        this.name = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}