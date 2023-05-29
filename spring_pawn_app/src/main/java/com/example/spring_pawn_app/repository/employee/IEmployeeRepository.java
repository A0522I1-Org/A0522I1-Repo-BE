package com.example.spring_pawn_app.repository.employee;
import com.example.spring_pawn_app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select e.id, e.address, e.avatar, e.date_of_birth, e.email, e.gender, e.id_card, e.is_flag, e.`name`, e.phone " +
                   "from employee as e inner join user as u on e.id = u.employee_id " +
                   "where u.user_name = :username", nativeQuery = true)
    Employee findEmployeeByUserName(@Param("username") String username);
    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: find by email
     * @param email
     * @return Null if result null or List<Employee> if result not null
     */
    List<Employee> findByEmail(String email);
    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: find by phone
     * @param phone
     * @return Null if result null or List<Employee> if result not null
     */
    List<Employee> findByPhone(String phone);
    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: find by idCard
     * @param idCard
     * @return Null if result null or List<Employee> if result not null
     */
    List<Employee> findByIdCard(String idCard);
    Employee findEmployeeByEmail(String email);
}
