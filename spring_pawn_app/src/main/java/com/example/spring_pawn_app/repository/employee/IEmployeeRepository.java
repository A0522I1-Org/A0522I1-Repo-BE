package com.example.spring_pawn_app.repository.employee;

import com.example.spring_pawn_app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select e.id, e.address, e.avatar, e.date_of_birth, e.email, e.gender, e.id_card, e.is_flag, e.`name`, e.phone " +
                   "from employee as e inner join user as u on e.id = u.employee_id " +
                   "where u.user_name = :username", nativeQuery = true)
    Employee findEmployeeByUserName(@Param("username") String username);
    Employee findEmployeeByEmail(String email);
}
