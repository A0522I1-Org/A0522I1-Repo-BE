package com.example.spring_pawn_app.repository.user;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByEmployee(Employee employee);
}
