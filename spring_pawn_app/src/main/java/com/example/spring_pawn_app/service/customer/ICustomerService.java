package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;

import java.util.Optional;

public interface ICustomerService {
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
