package com.example.spring_pawn_app.service.customer;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.model.Customer;

import java.util.Optional;

import com.example.spring_pawn_app.dto.CustomerListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ICustomerService {
    Customer findCustomerById(int idCustomer);

    Page<CustomerListDto> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer);

    Customer create (Customer customer);
}
