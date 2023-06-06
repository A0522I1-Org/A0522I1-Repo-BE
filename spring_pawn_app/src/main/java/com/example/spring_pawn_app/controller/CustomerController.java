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
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

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
        if (customerService.existsByCustomerCode(customerCode) != null) {
            return ResponseEntity.badRequest().body("Mã khách hàng đã tồn tại.");
        }

        // Kiểm tra email tồn tại
        if (customerService.existsByCustomerEmail(email) != null) {
            return ResponseEntity.badRequest().body("Email đã tồn tại.");
        }

        // Kiểm tra số điện thoại tồn tại
        if (customerService.existsByCustomerPhone(phone) != null) {
            return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại.");
        }

        // Kiểm tra số CMND tồn tại
        if (customerService.existsByCustomerIdentityCard(idCard) != null) {
            return ResponseEntity.badRequest().body("CMND hoặc Hộ chiếu đã tồn tại.");
        }

        // Thêm khách hàng mới
        CustomerDTO addedCustomer = customerService.addNewCustomer(customer);
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
        Optional<CustomerDTODetail> existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            // Lấy thông tin khách hàng hiện tại
            CustomerDTODetail currentCustomer = existingCustomer.get();

            // Kiểm tra customer code tồn tại
            if (!customer.getCustomerCode().equals(currentCustomer.getCustomerCode()) && customerService.existsByCustomerCode(customer.getCustomerCode()) != null) {
                return ResponseEntity.badRequest().body("Mã khách hàng đã tồn tại.");
            }

            // Kiểm tra email tồn tại
            if (!customer.getEmail().equals(currentCustomer.getEmail()) && customerService.existsByCustomerEmail(customer.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Email đã tồn tại.");
            }

            // Kiểm tra số điện thoại tồn tại
            if (!customer.getPhone().equals(currentCustomer.getPhone()) && customerService.existsByCustomerPhone(customer.getPhone()) != null) {
                return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại.");
            }

            // Kiểm tra số CMND tồn tại
            if (!customer.getIdentityCard().equals(currentCustomer.getIdentityCard()) && customerService.existsByCustomerIdentityCard(customer.getIdentityCard()) != null) {
                return ResponseEntity.badRequest().body("CMND hoặc Hộ chiếu đã tồn tại.");
            }

            // Cập nhật thông tin khách hàng
            customer.setId(id);
            customerService.updateCustomer(customer);
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
        Optional<CustomerDTODetail> optionalCustomer = customerService.getCustomerById(id);
        if (optionalCustomer.isPresent()) {
            CustomerDTODetail customerDto = optionalCustomer.get();
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}