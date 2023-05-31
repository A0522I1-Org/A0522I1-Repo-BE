package com.example.spring_pawn_app.service.contract;
import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContractService {

//    ContractDto findContractById(Integer id);
    ContractDto findContractById(Integer id);

    void updateContractPayment(Integer id);

    public Page<Contract> findAllContractWithPage(PageRequest pageRequest, String contractCode, String nameCustomer, String nameProduct, String beginDate);

    Page<Contract> findAllProductNotPay(Pageable page, String nameCustomer, String categoryId);
    Contract findContractNotPayById(int id);
    void saveContract(ContractCreateDto contractDto);

}
