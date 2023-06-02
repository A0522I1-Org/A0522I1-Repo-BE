package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import com.example.spring_pawn_app.dto.customer.HttpResponse;

import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.dto.customer.CustomerRegisterDTO;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    /**
     * Create by ThuongVTH
     * Date create: 02/06/2023
     * @param id
     * @return
     */
    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable("id") Integer id) {
        return iCustomerService.findCustomerById(id);
    }

    /**
     * Create by ThuongVTH
     * Date create: 02/06/2023
     * @param page
     * @param nameCustomer
     * @return
     */
    @GetMapping("/customer")
    public Page<CustomerListDto> findAllCustomersByNameWithPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "nameCustomer", defaultValue = "") String nameCustomer) {
        Page<CustomerListDto> customerPage = iCustomerService.findAllCustomerWithPage(PageRequest.of(page, 5), nameCustomer);
        return customerPage;
    }

    /**
     *Create by: ManPD
     *Date create: 21/5/2023
     *
     * @param customerRegisterDTO
     * @return HttpStatus.CREATED
     */
    @PostMapping("dangkynhanh")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRegisterDTO, customer);
        return new ResponseEntity<>(iCustomerService.createCustomer(customer), HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<HttpResponse> getAllCustomer(@RequestParam Optional<String> valueReceived,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> searchDateOfBirth,
                                                       @RequestParam Optional<Integer> searchGender,
                                                       @RequestParam Optional<Integer> page,
                                                       @RequestParam Optional<Integer> size) {

        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(5));
        Page<CustomerDTOList> customers = iCustomerService.getAllWithRequirement(
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
        Page<CustomerDTORestore> customerRestores = iCustomerService.getAllWithRequirementInRestore(valueReceived.orElse(""), pageable);

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
        Optional<CustomerDTODetail> optionalCustomer = iCustomerService.getCustomerById(id);
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
        Optional<CustomerDTODetail> customer = iCustomerService.getCustomerById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Xóa customer khỏi database
        iCustomerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> restoreCustomer(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Kiểm tra xem customer có tồn tại trong database hay không
        Optional<CustomerDTODetail> customer = iCustomerService.getCustomerByIdInRestore(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // khôi phục customer lại database
        iCustomerService.restoreCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


