package com.example.spring_pawn_app.service.user;

import com.example.spring_pawn_app.model.User;


public interface IUserService {
    User findByIdEmployee(Integer id);
    void save(User user);
}
