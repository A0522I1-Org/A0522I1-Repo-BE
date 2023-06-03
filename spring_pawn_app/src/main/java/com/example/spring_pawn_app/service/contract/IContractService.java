package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;

import java.util.List;

public interface IContractService {

    ContractDto findContractById(Integer id);

    void updateContractLiquidation(Integer id);
    List<Contract> findContractByCustomerId(Integer id);


}
