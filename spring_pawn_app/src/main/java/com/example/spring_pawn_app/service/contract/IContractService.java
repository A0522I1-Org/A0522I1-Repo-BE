package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.dto.contract.ContractCreateDto;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface IContractService {

//    ContractDto findContractById(Integer id);
    ContractDto findContractById(Integer id);

    void updateContractPayment(Integer id);

    public Page<Contract> findAllContractWithPage(PageRequest pageRequest, String contractCode, String nameCustomer, String nameProduct, String beginDate);

    Contract findContractNotPayById(int id);
    void saveContract(ContractCreateDto contractDto);

    void edit(ContractEditDto contractEditDto);
    ContractEditDto findById(Integer id);
    void delete(Integer id);
    Page<Contract> findAll(Pageable pageable);

    Page<Contract> search(String customerName, String productName, String beforeDate,
                          String afterDate, String status, Pageable pageable);


    Page<Contract> findAllProductNotPay(Pageable page, String nameCustomer, String categoryName);
    Contract findById(int id);


}
