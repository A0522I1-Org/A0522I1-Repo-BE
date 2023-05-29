package com.example.spring_pawn_app.repository.user;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: find by employee
     *
     * @param employee
     * @return Null if result null or User if result not null
     */
    User findByEmployee(Employee employee);
}

