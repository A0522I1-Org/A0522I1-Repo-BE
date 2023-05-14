package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IContractService {
    Page<Contract> findAllProductNotPay(PageRequest page, String nameCustomer, String categoryId);
    Contract findById(int id);
}
