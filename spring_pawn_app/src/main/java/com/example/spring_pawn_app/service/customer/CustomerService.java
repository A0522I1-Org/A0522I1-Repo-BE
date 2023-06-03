package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class CustomerService implements ICustomerService{

   @Autowired
   ICustomerRepository iCustomerRepository;

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @Override
    public Page<Customer> findByCustomer(String customer_name, PageRequest page) {
        return iCustomerRepository.findByCustomer(customer_name, page );
    }

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @Override
    public Customer findCustomerById(Integer id) {
        return iCustomerRepository.findCustomerById( id );
    }
}
