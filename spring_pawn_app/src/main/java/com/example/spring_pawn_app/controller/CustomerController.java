package com.example.spring_pawn_app.controller;
import com.example.spring_pawn_app.model.Customer;

import com.example.spring_pawn_app.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @GetMapping("/customers")
    public ResponseEntity<Page<Customer>> findAll(@RequestParam(value = "customer_name",defaultValue = "") String customer_name,
                                                 @RequestParam(defaultValue = "0") int page) {
        Page<Customer> customerPage = iCustomerService.findByCustomer(customer_name,PageRequest.of( page,5 ) );
        if (customerPage == null){
            return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Page<Customer>>( customerPage,HttpStatus.OK);
    }

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @GetMapping("/customers/{id}")
    public Customer findById(@PathVariable("id") int id){
       return iCustomerService.findCustomerById( id );
    }
}

