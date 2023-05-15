package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService{
    @Autowired
    IContractRepository iContractRepository;
    @Override
    public ContractDto findContractById(Integer id) {
        ContractDto contractDto = new ContractDto();
        Contract contract = iContractRepository.findContractById(id);
        BeanUtils.copyProperties(contract,contractDto);
        return contractDto;
    }

    @Override
    public void updateContractPayment(Integer id) {
        iContractRepository.updateContractPayment(id);
    }

}
