package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;


public interface ICustomerService {
    /**
     * @author Trần Thế Huy
     * @version 6
     * @since 6/6/2023
     */
    Optional<CustomerDTODetail> getCustomerById(Integer id);

    Optional<CustomerDTODetail> getCustomerByIdInRestore(Integer id);

    void deleteCustomerById(Integer id);

    void restoreCustomerById(Integer id);

    Page<CustomerDTOList> getAllWithRequirement(String valueReceived, LocalDate searchDateOfBirth, Integer searchGender, Pageable pageable);

    Page<CustomerDTORestore> getAllWithRequirementInRestore(String valueReceived, Pageable pageable);
}
