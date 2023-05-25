package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

public interface IContractService {

    ContractDto findContractById(Integer id);

    void updateContractLiquidation(Integer id);
    Page<Contract> findContractByCustomerId(PageRequest page, Integer id);


}
