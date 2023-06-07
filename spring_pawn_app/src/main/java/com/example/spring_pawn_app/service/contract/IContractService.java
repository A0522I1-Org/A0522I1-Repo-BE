package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.contract.ContractEditDto;
import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.dto.contract.ContractCreateDto;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContractService {

//    ContractDto findContractById(Integer id);
    ContractDto findContractById(Integer id);

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
//    void updateContractLiquidation(Integer id);
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

    List<Contract> findContractByCustomerId(Integer id);
}
