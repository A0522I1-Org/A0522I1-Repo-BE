package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.customer.CustomerRegisterDTO;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("customer")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRegisterDTO,customer);
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }
}
