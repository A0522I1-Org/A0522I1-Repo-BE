package com.example.spring_pawn_app.service.customer;


import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import com.example.spring_pawn_app.mapper.CustomerMapper;
import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository iCustomerRepository;
    @Autowired
    private CustomerMapper customerMapper;

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

    /**
     * @author Trần Thế Huy
     * @version 1
     * @since 28/5/2023
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTODetail> getCustomerById(Integer id) {
        List<Object[]> results = this.iCustomerRepository.getCustomerById(id);
        return results.stream()
                .findFirst()
                .map(customerMapper::mapToDtoDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTODetail> getCustomerByIdInRestore(Integer id) {
        List<Object[]> results = this.iCustomerRepository.getCustomerByIdInRestore(id);
        return results.stream()
                .findFirst()
                .map(customerMapper::mapToDtoDetail);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        this.iCustomerRepository.deleteCustomerById(id);
    }

    @Override
    public void restoreCustomerById(Integer id) {
        this.iCustomerRepository.restoreCustomerById(id);
    }

    @Override
    public Page<CustomerDTOList> getAllWithRequirement(String valueReceived, LocalDate searchDateOfBirth, Integer searchGender, Pageable pageable) {
        Page<Object[]> resultPage = this.iCustomerRepository.getAllWithRequirement(valueReceived, searchDateOfBirth, searchGender, pageable);
        List<CustomerDTOList> customerDtoList = resultPage.getContent()
                .stream()
                .map(customerMapper::mapToDtoList)
                .collect(Collectors.toList());
        return new PageImpl<>(customerDtoList, pageable, resultPage.getTotalElements());
    }

    @Override
    public Page<CustomerDTORestore> getAllWithRequirementInRestore(String valueReceived, Pageable pageable) {
        Page<Object[]> resultPage = this.iCustomerRepository.getAllWithRequirementInRestore(valueReceived, pageable);
        List<CustomerDTORestore> customerDtoRestore = resultPage.getContent()
                .stream()
                .map(customerMapper::mapToDtoRestore)
                .collect(Collectors.toList());
        return new PageImpl<>(customerDtoRestore, pageable, resultPage.getTotalElements());
    }
}
