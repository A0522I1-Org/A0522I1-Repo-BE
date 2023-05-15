package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;

public interface IContractService {

    ContractDto findContractById(Integer id);

    void updateContractPayment(Integer id);


}
