package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

public interface ICustomerService {
    Page<Customer> findByCustomer(String customer_name, PageRequest page);
    Customer findCustomerById(Integer id);



}
