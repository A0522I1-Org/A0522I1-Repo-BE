package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

}
