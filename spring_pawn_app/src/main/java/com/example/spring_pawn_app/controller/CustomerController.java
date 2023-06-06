package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import com.google.api.client.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.dto.contract.CustomerRegisterDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;

import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
     *
     * @param id
     * @return
     */
    @GetMapping("/customers/{id}")
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
    @GetMapping("/customers")
    public Page<CustomerListDto> findAllCustomersByNameWithPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "nameCustomer", defaultValue = "") String nameCustomer) {
        Page<CustomerListDto> customerPage = iCustomerService.findAllCustomerWithPage(PageRequest.of(page, 5), nameCustomer);
        return customerPage;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = new Customer();
        customer.setAddress(customerRegisterDTO.getAddress());
        customer.setEmail(customerRegisterDTO.getEmailCustomer());
        customer.setName(customerRegisterDTO.getNameCustomer());
        customer.setPhone(customerRegisterDTO.getPhoneCustomer());
        customer.setNote(customerRegisterDTO.getNote());
        return new ResponseEntity<>(iCustomerService.create(customer), HttpStatus.CREATED);
    }

    /**
     * @author Trần Thế Huy
     * @version 1
     * @since 28/5/2023
     */
    @GetMapping()
    public ResponseEntity<?> getAllCustomer(@RequestParam Optional<String> valueReceived,
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
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("restore")
    public ResponseEntity<?> getAllCustomerRestore(@RequestParam Optional<String> valueReceived,
                                                   @RequestParam Optional<Integer> page,
                                                   @RequestParam Optional<Integer> size) {

        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(5));
        Page<CustomerDTORestore> customerRestores = iCustomerService.getAllWithRequirementInRestore(valueReceived.orElse(""), pageable);

        if (customerRestores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @PostMapping("add")
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

    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @PutMapping("update/{id}")
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
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTODetail> getCustomerById(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<CustomerDTODetail> optionalCustomer = iCustomerService.getCustomerById(id);
        if (optionalCustomer.isPresent()) {
            CustomerDTODetail customerDto = optionalCustomer.get();
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
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
}
