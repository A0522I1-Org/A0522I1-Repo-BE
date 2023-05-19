package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService{
    @Autowired
    IContractRepository iContractRepository;

    @Override
    public void saveContract(ContractCreateDto contractDto) {
        Contract contract = new Contract();
        Product product = new Product(contractDto.getNameProduct(), contractDto.getPrice(), contractDto.getCategory());
        contract.setContractCode(contractDto.getContractCode());
        contract.setBeginDate(contractDto.getBeginDate());
        contract.setEndDate(contractDto.getEndDate());
        contract.setCustomer(contractDto.getCustomer());
        contract.setInterest(contractDto.getInterest());
        contract.setStatus(contractDto.getStatus());
        contract.setProduct(product);
        iContractRepository.save(contract);
    }
}
