package com.example.spring_pawn_app.service.customer;

// <<<<<<< main
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.mapper.CustomerMapper;
=======
import com.example.spring_pawn_app.model.Customer;
// >>>>>>> ManPD
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// <<<<<<< main
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public Optional<CustomerDTODetail> getCustomerById(Integer id) {
        List<Object[]> results = customerRepository.getCustomerById(id);
        return results.stream()
                .findFirst()
                .map(customerMapper::mapToDTODetail);
=======
public class CustomerService implements ICustomerService<Customer>{
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
// >>>>>>> ManPD
    }
}
