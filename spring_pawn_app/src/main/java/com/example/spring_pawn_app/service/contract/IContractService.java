package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;

import java.util.List;

public interface IContractService {


    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
//    void updateContractLiquidation(Integer id);

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    List<Contract> findContractByCustomerId(Integer id);


}
