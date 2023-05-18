package com.example.spring_pawn_app.service.customer;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Optional<CustomerDTODetail> getCustomerById(Integer id);

    Customer create (Customer customer);

}
