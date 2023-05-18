package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;

import java.util.Optional;

public interface ICustomerService {
    Optional<CustomerDTODetail> getCustomerById(Integer id);
    CustomerDTO addNewCustomer(CustomerDTO customer);

    void updateCustomer(CustomerDTO customer);
}
