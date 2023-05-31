package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository iCustomerRepository;

    /**
     * Create by: ManPD
     * Date create: 21/5/2023
     *
     * @param customer
     */
    @Override
    public Customer createCustomer(Customer customer) {
        return iCustomerRepository.save(customer);
    }
}
