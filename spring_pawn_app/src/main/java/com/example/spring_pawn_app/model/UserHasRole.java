package com.example.spring_pawn_app.model;

import javax.persistence.*;

@Entity
public class UserHasRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "varchar(200)")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition = "varchar(200)")
    private Role role;

    public UserHasRole() {
    }

    public UserHasRole(Integer id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
