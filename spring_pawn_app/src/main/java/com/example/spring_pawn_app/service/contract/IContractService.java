package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;

public interface IContractService {
    ContractDto findContractById(Integer id);

    void updateContractPayment(Integer id);
    void saveContract(ContractDto contractDto);
}
