package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IContractService {
//    ContractDto findContractById(Integer id);
    ContractDto findContractById(Integer id);

    void updateContractPayment(Integer id);

    public Page<Contract> findAllContractWithPage(PageRequest pageRequest, String contractCode, String nameCustomer, String nameProduct, String beginDate);

}
