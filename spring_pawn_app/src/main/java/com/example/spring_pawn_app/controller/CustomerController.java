package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.customer.*;
import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import com.example.spring_pawn_app.dto.customer.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
     *
     * @param id
     * @return
     */
    @GetMapping("customer/{id}")
    public Customer findCustomerById(@PathVariable("id") Integer id) {
        return iCustomerService.findCustomerById(id);
    }

    /**
     * Create by ThuongVTH
     * Date create: 02/06/2023
     *
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
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = new Customer();
        customer.setName(customerRegisterDTO.getCustomerName());
        customer.setEmail(customerRegisterDTO.getEmail());
        customer.setPhone(customerRegisterDTO.getPhone());
        customer.setAddress(customerRegisterDTO.getAddress());
        customer.setNote(customerRegisterDTO.getNote());
        customer.setIdentityCard(customerRegisterDTO.getIdCardCustomer());
        customer.setCustomerCode(iCustomerService.createCustomerCode());
        return new ResponseEntity<>(iCustomerService.createCustomer(customer), HttpStatus.CREATED);
    }

    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @PostMapping("/add")
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerDTO customer) {
        String customerCode = customer.getCustomerCode();
        String email = customer.getEmail();
        String phone = customer.getPhone();
        String idCard = customer.getIdentityCard();

        // Kiểm tra customer code tồn tại
        if (iCustomerService.existsByCustomerCode(customerCode) != null) {
            return ResponseEntity.badRequest().body("Mã khách hàng đã tồn tại.");
        }

        // Kiểm tra email tồn tại
        if (iCustomerService.existsByCustomerEmail(email) != null) {
            return ResponseEntity.badRequest().body("Email đã tồn tại.");
        }

        // Kiểm tra số điện thoại tồn tại
        if (iCustomerService.existsByCustomerPhone(phone) != null) {
            return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại.");
        }

        // Kiểm tra số CMND tồn tại
        if (iCustomerService.existsByCustomerIdentityCard(idCard) != null) {
            return ResponseEntity.badRequest().body("CMND hoặc Hộ chiếu đã tồn tại.");
        }

        // Thêm khách hàng mới
        CustomerDTO addedCustomer = iCustomerService.addNewCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customer) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra xem khách hàng có tồn tại trong cơ sở dữ liệu hay không
        Optional<CustomerDTODetail> existingCustomer = iCustomerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            // Lấy thông tin khách hàng hiện tại
            CustomerDTODetail currentCustomer = existingCustomer.get();

            // Kiểm tra customer code tồn tại
            if (!customer.getCustomerCode().equals(currentCustomer.getCustomerCode()) && iCustomerService.existsByCustomerCode(customer.getCustomerCode()) != null) {
                return ResponseEntity.badRequest().body("Mã khách hàng đã tồn tại.");
            }

            // Kiểm tra email tồn tại
            if (!customer.getEmail().equals(currentCustomer.getEmail()) && iCustomerService.existsByCustomerEmail(customer.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Email đã tồn tại.");
            }

            // Kiểm tra số điện thoại tồn tại
            if (!customer.getPhone().equals(currentCustomer.getPhone()) && iCustomerService.existsByCustomerPhone(customer.getPhone()) != null) {
                return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại.");
            }

            // Kiểm tra số CMND tồn tại
            if (!customer.getIdentityCard().equals(currentCustomer.getIdentityCard()) && iCustomerService.existsByCustomerIdentityCard(customer.getIdentityCard()) != null) {
                return ResponseEntity.badRequest().body("CMND hoặc Hộ chiếu đã tồn tại.");
            }

            // Cập nhật thông tin khách hàng
            customer.setId(id);
            iCustomerService.updateCustomer(customer);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.ok().body("Customer not found");
        }
    }

    /**
     * @author Trần Thế Huy
     * @version 2
     * @since 6/6/2023
     */
    @GetMapping("/customers")
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

    @GetMapping("/restore")
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

    @GetMapping("/customers/{id}")
    public ResponseEntity<HttpResponse> getCustomerById(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<CustomerDTODetail> optionalCustomer = iCustomerService.getCustomerById(id);
        System.out.println(optionalCustomer);
        if (optionalCustomer.get().getCustomerCode() != null) {
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

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Kiểm tra xem customer có tồn tại trong database hay không
        Optional<CustomerDTODetail> customer = iCustomerService.getCustomerById(id);
        System.out.println(customer);
        if (customer.get().getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Xóa customer khỏi database
        iCustomerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<?> restoreCustomer(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Kiểm tra xem customer có tồn tại trong database hay không
        Optional<CustomerDTODetail> customer = iCustomerService.getCustomerByIdInRestore(id);
        System.out.println(customer);
        if (customer.get().getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // khôi phục customer lại database
        iCustomerService.restoreCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     */

    @GetMapping("/customers/liquidation")
    public ResponseEntity<Page<Customer>> findAll(@RequestParam(value = "customer_name", defaultValue = "") String customer_name,
                                                  @RequestParam(defaultValue = "0") int page) {
        Page<Customer> customerPage = iCustomerService.findByCustomer(customer_name, PageRequest.of(page, 5));
        if (customerPage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Page<Customer>>(customerPage, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
