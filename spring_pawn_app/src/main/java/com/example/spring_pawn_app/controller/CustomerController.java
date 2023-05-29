package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.CustomerListDto;
import com.example.spring_pawn_app.dto.CustomerRegisterDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import com.example.spring_pawn_app.dto.customer.HttpResponse;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

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
        Page<CustomerListDto> customerPage = customerService.findAllCustomerWithPage(PageRequest.of(page, 2), nameCustomer);
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

    @GetMapping()
    public ResponseEntity<HttpResponse> getAllCustomer(@RequestParam Optional<String> valueReceived,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> searchDateOfBirth,
                                                       @RequestParam Optional<Integer> searchGender,
                                                       @RequestParam Optional<Integer> page,
                                                       @RequestParam Optional<Integer> size) {

        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(5));
        Page<CustomerDTOList> customers = customerService.getAllWithRequirement(
                valueReceived.orElse(""), searchDateOfBirth.orElse(null), searchGender.orElse(null), pageable
        );

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Collections.singletonMap("page", customers))
                        .message("Customers Retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("restore")
    public ResponseEntity<HttpResponse> getAllCustomerRestore(@RequestParam Optional<String> valueReceived,
                                                              @RequestParam Optional<Integer> page,
                                                              @RequestParam Optional<Integer> size) {

        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(5));
        Page<CustomerDTORestore> customerRestores = customerService.getAllWithRequirementInRestore(valueReceived.orElse(""), pageable);

        if (customerRestores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Collections.singletonMap("page", customerRestores))
                        .message("Customer Restores Retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<HttpResponse> getCustomerById(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<CustomerDTODetail> optionalCustomer = customerService.getCustomerById(id);
        if (optionalCustomer.isPresent()) {
            CustomerDTODetail customerDtoDetail = optionalCustomer.get();
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .data(Collections.singletonMap("content", customerDtoDetail))
                            .message("Customer Restores Retrieved")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Kiểm tra xem customer có tồn tại trong database hay không
        Optional<CustomerDTODetail> customer = customerService.getCustomerById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Xóa customer khỏi database
        customerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> restoreCustomer(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Kiểm tra xem customer có tồn tại trong database hay không
        Optional<CustomerDTODetail> customer = customerService.getCustomerByIdInRestore(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // khôi phục customer lại database
        customerService.restoreCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


