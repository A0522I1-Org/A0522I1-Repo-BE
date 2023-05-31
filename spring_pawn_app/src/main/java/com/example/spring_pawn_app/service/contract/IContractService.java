package com.example.spring_pawn_app.service.contract;
import com.example.spring_pawn_app.dto.contract.ContractCreateDto;


import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService {
    Page<Contract> findAllProductNotPay(Pageable page, String nameCustomer, String categoryId);
    Contract findById(int id);
    void saveContract(ContractCreateDto contractDto);

}
