package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.CustomerListDto;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;

    @GetMapping("/customer/{id}")
    public Customer findCustomerByIdOrName(@PathVariable("id") Integer id){
        return iCustomerService.findCustomerById(id);
    }

    @GetMapping("/customer")
    public Page<CustomerListDto> findAllCustomersByNameWithPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "nameCustomer", defaultValue = "") String nameCustomer){
        Page<CustomerListDto> customerPage = iCustomerService.findAllCustomerWithPage(PageRequest.of(page,1), nameCustomer);
        return customerPage;
    }
}
