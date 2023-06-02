package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractCreateDto;

public interface IContractService {
    void saveContract(ContractCreateDto contractDto);
}
