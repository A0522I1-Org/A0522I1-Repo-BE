package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.model.Customer;

import java.util.Optional;

import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ICustomerService {
    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     */
    Page<Customer> findByCustomer(String customer_name, PageRequest page);

    Customer findCustomerById(Integer idCustomer);

    Page<CustomerListDto> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer);

    /**
     * Create by: ManPD
     * Date create: 21/5/2023
     *
     * @param customer
     * @return customer
     */
    Customer createCustomer(Customer customer);


    /**
     * * Create by: ManPD
     * Date create: 6/6/2023
     *
     * @return String
     */
    String createCustomerCode();

    /**
     * Create by: ManPD
     * Date create: 6/6/2023
     *
     * @return customer
     */
    Customer getCustomerByCustomerCode(String customerCode);

    /**
     * @author Trần Thế Huy
     * @version 2
     * @since 6/6/2023
     */
    Optional<CustomerDTODetail> getCustomerById(Integer id);

    Optional<CustomerDTODetail> getCustomerByIdInRestore(Integer id);

    void deleteCustomerById(Integer id);

    void restoreCustomerById(Integer id);

    Page<CustomerDTOList> getAllWithRequirement(String valueReceived, LocalDate searchDateOfBirth, Integer searchGender, Pageable pageable);

    Page<CustomerDTORestore> getAllWithRequirementInRestore(String valueReceived, Pageable pageable);

    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    CustomerDTO addNewCustomer(CustomerDTO customer);

    void updateCustomer(CustomerDTO customer);

    String existsByCustomerEmail(String email);

    String existsByCustomerPhone(String phone);

    String existsByCustomerIdentityCard(String idCard);

    String existsByCustomerCode(String code);
}
