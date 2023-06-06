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
    private ICustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

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
        // Kiểm tra xem khách hàng có tồn tại trong cơ sở dữ liệu hay không
        Optional<CustomerDTODetail> existingCustomer = getCustomerById(customer.getId());
        if (existingCustomer.isPresent()) {
            // Cập nhật thông tin khách hàng
            CustomerDTODetail customerToUpdate = existingCustomer.get();
            customerToUpdate.setCustomerCode(customer.getCustomerCode());
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setGender(customer.getGender());
            customerToUpdate.setDateOfBirth(customer.getDateOfBirth());
            customerToUpdate.setIdentityCard(customer.getIdentityCard());
            customerToUpdate.setPhone(customer.getPhone());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAddress(customer.getAddress());
            customerToUpdate.setAvatar(customer.getAvatar());

            // Lưu thông tin khách hàng đã được cập nhật vào cơ sở dữ liệu
            customerRepository.updateCustomer(customer);
        }
    }

    @Override
    public String existsByCustomerEmail(String email) {
        return customerRepository.existsByCustomerEmail(email);
    }

    @Override
    public String existsByCustomerPhone(String phone) {
        return customerRepository.existsByCustomerPhone(phone);
    }

    @Override
    public String existsByCustomerIdentityCard(String idCard) {
        return customerRepository.existsByCustomerIdentityCard(idCard);
    }

    @Override
    public String existsByCustomerCode(String code) {
        return customerRepository.existsByCustomerCode(code);
    }
}
