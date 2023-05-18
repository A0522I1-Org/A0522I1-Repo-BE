package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addnewcustomer")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO addedCustomer = customerService.addNewCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTODetail> getCustomerById(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<CustomerDTODetail> optionalCustomer = customerService.getCustomerById(id);
        if (optionalCustomer.isPresent()) {
            CustomerDTODetail customerDto = optionalCustomer.get();
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
