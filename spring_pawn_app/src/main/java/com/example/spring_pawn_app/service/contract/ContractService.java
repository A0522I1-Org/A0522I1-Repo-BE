package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository iContractRepository;

    @Override
    public Page<Contract> findAllProductNotPay(PageRequest page, String nameCustomer, String categoryId) {
        return iContractRepository.findAllProductNotPay( nameCustomer, categoryId, page);
    }

    @Override
    public Contract findById(int id) {
        return iContractRepository.findContractByID(id);
    }
}
