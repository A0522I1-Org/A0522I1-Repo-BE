package com.example.spring_pawn_app.service.customer;

// <<<<<<< main
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;

import java.util.Optional;

public interface ICustomerService {
    Optional<CustomerDTODetail> getCustomerById(Integer id);
=======
public interface ICustomerService<T> {

    T create (T t);
// >>>>>>> ManPD
}
