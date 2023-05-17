package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.dto.contract.ContractProjection;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Override
    public Page<ContractProjection> findAllContractWithPage(PageRequest pageRequest,String contractCode, String nameCustomer, String nameProduct, String beginDate) {
        return iContractRepository.findAllContractWithPage(pageRequest,contractCode,nameCustomer,nameProduct,beginDate);
//        return iContractRepository.findAllContractWithPage(ContractProjection.class);
    }


}
