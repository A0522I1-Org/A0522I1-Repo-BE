package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Query(value = "SELECT c.customerCode, c.name, c.gender, c.dateOfBirth, c.identityCard, c.phone, c.email, " +
            "c.address, c.avatar, c.note, COUNT(con.id) " +
            "FROM Customer c " +
            "LEFT JOIN Contract con ON con.customer.id = c.id " +
            "WHERE c.id = ?1 AND c.isFlag = FALSE")
    List<Object[]> getCustomerById(Integer id);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer (customer_code, name, gender, date_of_birth," +
            "id_card, phone, email, address, avatar) " +
            "VALUES (:#{#customerDTO.customerCode}, :#{#customerDTO.name}, :#{#customerDTO.gender}, " +
            ":#{#customerDTO.dateOfBirth}, :#{#customerDTO.identityCard}, :#{#customerDTO.phone}, " +
            ":#{#customerDTO.email}, :#{#customerDTO.address}, :#{#customerDTO.avatar})", nativeQuery = true)
    void createCustomer(@Param("customerDTO") CustomerDTO customerDTO);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer " +
            "SET customer_code = :#{#customerDTO.customerCode}, " +
            "name = :#{#customerDTO.name}, " +
            "gender = :#{#customerDTO.gender}, " +
            "date_of_birth = :#{#customerDTO.dateOfBirth}, " +
            "id_card = :#{#customerDTO.identityCard}, " +
            "phone = :#{#customerDTO.phone}, " +
            "email = :#{#customerDTO.email}, " +
            "address = :#{#customerDTO.address}, " +
            "avatar = :#{#customerDTO.avatar} " +
            "WHERE id = :#{#customerDTO.id}", nativeQuery = true)
    void updateCustomer(@Param("customerDTO") CustomerDTO customerDTO);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Query(value = "select email from customer where email = ?1", nativeQuery = true)
    String existsByCustomerEmail(String email);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Query(value = "select phone from customer where phone = ?1", nativeQuery = true)
    String existsByCustomerPhone(String phoneNumber);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Query(value = "select id_card from customer where id_card = ?1", nativeQuery = true)
    String existsByCustomerIdentityCard(String idCard);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Query(value = "select customer_code from customer where customer_code = ?1", nativeQuery = true)
    String existsByCustomerCode(String code);

}
