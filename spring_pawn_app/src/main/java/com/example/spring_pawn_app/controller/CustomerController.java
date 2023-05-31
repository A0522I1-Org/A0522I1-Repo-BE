package com.example.spring_pawn_app.controller;


import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.dto.customer.CustomerRegisterDTO;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable("id") Integer id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/customer")
    public Page<CustomerListDto> findAllCustomersByNameWithPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "nameCustomer", defaultValue = "") String nameCustomer) {
        Page<CustomerListDto> customerPage = customerService.findAllCustomerWithPage(PageRequest.of(page, 3), nameCustomer);
        return customerPage;
    }


    @PostMapping("/customer")
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
