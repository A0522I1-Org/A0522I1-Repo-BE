package com.example.spring_pawn_app.service.customer;
import com.example.spring_pawn_app.model.Customer;

import java.util.Optional;

import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

/**
 * @author Trần Thế Huy
 * @version 1
 * @since 28/5/2023
 */
public interface ICustomerService {
    Customer findCustomerById(int idCustomer);

    Page<CustomerListDto> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer);

    /**
     * Create by: ManPD
     *Date create: 21/5/2023
     *
     * @param customer
     * @return customer
     */
    Customer createCustomer(Customer customer);
  
    Optional<CustomerDTODetail> getCustomerById(Integer id);

    Optional<CustomerDTODetail> getCustomerByIdInRestore(Integer id);

    void deleteCustomerById(Integer id);

    void restoreCustomerById(Integer id);

    Page<CustomerDTOList> getAllWithRequirement(String valueReceived, LocalDate searchDateOfBirth, Integer searchGender, Pageable pageable);

    Page<CustomerDTORestore> getAllWithRequirementInRestore(String valueReceived, Pageable pageable);
}
