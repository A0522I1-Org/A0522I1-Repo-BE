package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.mapper.CustomerMapper;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    }

    @Override
    public CustomerDTO addNewCustomer(CustomerDTO customer) {
        customerRepository.createCustomer(customer);
        return customer;
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        Optional<CustomerDTODetail> existingCustomerOptional = getCustomerById(customer.getId());
        if (existingCustomerOptional.isPresent()) {
            CustomerDTODetail existingCustomer = existingCustomerOptional.get();

            // Update the properties of the existing customer
            existingCustomer.setCustomerCode(customer.getCustomerCode());
            existingCustomer.setName(customer.getName());
            existingCustomer.setGender(customer.getGender());
            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
            existingCustomer.setIdentityCard(customer.getIdentityCard());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setAvatar(customer.getAvatar());

            // Save the updated customer
            addNewCustomer(existingCustomer);
        } else {
            // Handle the case when the customer does not exist
            // You can throw an exception or handle it based on your business logic
        }
    }
}
