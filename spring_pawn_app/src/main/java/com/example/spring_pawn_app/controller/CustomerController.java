package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.customer.CustomerRegisterDTO;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    // add new customer by ManPD
    //        Start
    @PostMapping("dangkynhanh")
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRegisterDTO, customer);
        return new ResponseEntity<>(iCustomerService.createCustomer(customer), HttpStatus.CREATED);
    }
    //    End
}
