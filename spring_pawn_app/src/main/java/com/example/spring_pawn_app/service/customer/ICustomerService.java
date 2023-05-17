package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.CustomerDto;
import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerService {
    Customer findCustomerById(int idCustomer);

    Page<Customer> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer);
}
