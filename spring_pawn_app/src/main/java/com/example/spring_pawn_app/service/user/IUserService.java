package com.example.spring_pawn_app.service.user;

import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.model.Employee;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    User findByIdEmployee(Integer id);
    void save(User user);
    Optional<User> findByEmployee(Employee employee);

}
