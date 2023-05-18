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
    @Query(value = "SELECT c.customerCode, c.name, c.gender, c.dateOfBirth, c.identityCard, c.phone, c.email, " +
            "c.address, c.avatar, c.note, COUNT(con.id) " +
            "FROM Customer c " +
            "LEFT JOIN Contract con ON con.customer.id = c.id " +
            "WHERE c.id = ?1 AND c.isFlag = FALSE")
    List<Object[]> getCustomerById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer (customer_code, customer_name, gender, date_of_birth," +
            "id_card, phone, email, address, avatar) " +
            "VALUES (:#{#customerDTO.customerCode}, :#{#customerDTO.name}, :#{#customerDTO.gender}, " +
            ":#{#customerDTO.dateOfBirth}, :#{#customerDTO.identityCard}, :#{#customerDTO.phone}, " +
            ":#{#customerDTO.email}, :#{#customerDTO.address}, :#{#customerDTO.avatar})", nativeQuery = true)
    void createCustomer(@Param("customerDTO") CustomerDTO customerDTO);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer " +
            "SET customer_code = :#{#customerDTO.customerCode}, " +
            "customer_name = :#{#customerDTO.name}, " +
            "gender = :#{#customerDTO.gender}, " +
            "date_of_birth = :#{#customerDTO.dateOfBirth}, " +
            "id_card = :#{#customerDTO.identityCard}, " +
            "phone = :#{#customerDTO.phone}, " +
            "email = :#{#customerDTO.email}, " +
            "address = :#{#customerDTO.address}, " +
            "avatar = :#{#customerDTO.avatar} " +
            "WHERE id = :#{#customerDTO.id}", nativeQuery = true)
    void updateCustomer(@Param("customerDTO") CustomerDTO customerDTO);



}
