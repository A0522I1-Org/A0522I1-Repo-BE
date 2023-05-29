package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{
    /**
     * Created by: PhongTD
     * Date created: 16/05/2023
     * @param customerNameEdit
     * @param id
     */
    @Query("UPDATE Customer SET name = ?1 WHERE id = ?2")
    @Modifying
    void updateCustomerName(String customerNameEdit, Integer id);
}
