package com.example.spring_pawn_app.service.customer;


import com.example.spring_pawn_app.dto.contract.CustomerListDto;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepository iCustomerRepository;
    @Override
    public Customer findCustomerById(int idCustomer) {
        return iCustomerRepository.findCustomerById(idCustomer);
    }

    @Override
    public Page<CustomerListDto> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer) {
        return iCustomerRepository.findAllCustomerWithPage(pageRequest, nameCustomer);
    }
    @Override
    public Customer create(Customer customer) {
        return this.iCustomerRepository.save(customer);
    }
}
