package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.CustomerRegisterDTO;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.CustomerService;
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
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = new Customer();
        customer.setAddress(customerRegisterDTO.getAddress());
        customer.setEmail(customerRegisterDTO.getEmailCustomer());
        customer.setName(customerRegisterDTO.getNameCustomer());
        customer.setPhone(customerRegisterDTO.getPhoneCustomer());
        customer.setNote(customerRegisterDTO.getNote());
        return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
    }
}
