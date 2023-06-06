package com.example.spring_pawn_app.service.customer;
import com.example.spring_pawn_app.dto.customer.CustomerDTO;
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
    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    Page<Customer> findByCustomer(String customer_name, PageRequest page);
    Customer findCustomerById(Integer idCustomer);

    Page<CustomerListDto> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer);

    Customer create (Customer customer);

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
    Optional<CustomerDTODetail> getCustomerById(Integer id);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    CustomerDTO addNewCustomer(CustomerDTO customer);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    void updateCustomer(CustomerDTO customer);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    String existsByCustomerEmail(String email);

    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    String existsByCustomerPhone(String phone);

    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    String existsByCustomerIdentityCard(String idCard);
    /**
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    String existsByCustomerCode(String code);
}
