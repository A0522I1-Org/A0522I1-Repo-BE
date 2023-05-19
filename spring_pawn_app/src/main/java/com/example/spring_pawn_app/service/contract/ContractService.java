package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Page<Contract> findAllContractWithPage(PageRequest pageRequest,String contractCode, String nameCustomer, String nameProduct, String beginDate) {
        Page<Contract> contractPage = null;
        if (!contractCode.equals("") && nameCustomer.equals("") && nameProduct.equals("") && beginDate.equals("") ) {
            contractPage = iContractRepository.findAllContractByContractCodeWithPage(pageRequest,contractCode);
        }
        if(!nameCustomer.isEmpty() && contractCode.isEmpty() && nameProduct.isEmpty() && beginDate.isEmpty()){
            contractPage = iContractRepository.findAllContractByNameCustomerWithPage(pageRequest,nameCustomer);
        }
        if(!nameProduct.equals("") && nameCustomer.equals("") && contractCode.equals("") && beginDate.equals("")){
            contractPage = iContractRepository.findAllContractByNameProductWithPage(pageRequest,nameProduct);
        }
        if(!beginDate.equals("") && nameCustomer.equals("") && contractCode.equals("") && nameProduct.equals("")){
            contractPage = iContractRepository.findAllContractByBeginDateWithPage(pageRequest,beginDate);
        }
        if(!beginDate.equals("") && !nameCustomer.equals("") && contractCode.equals("") && nameProduct.equals("")){
            contractPage = iContractRepository.findAllContractByNameCustomerAndBeginDate(pageRequest,nameCustomer,beginDate);
        }
        if(!beginDate.equals("") && !contractCode.equals("") && nameCustomer.equals("") && nameProduct.equals("")){
            contractPage = iContractRepository.findAllContractByContractCodeAndBeginDate(pageRequest,contractCode,beginDate);
        }
        if(!beginDate.equals("") && !nameProduct.equals("") && nameCustomer.equals("") && contractCode.equals("")){
            contractPage = iContractRepository.findAllContractByNameProductAndBeginDate(pageRequest,nameProduct,beginDate);
        }
        if(!nameCustomer.equals("") && !contractCode.equals("") && nameProduct.equals("") && beginDate.equals("")){
            contractPage = iContractRepository.findAllContractByNameCustomerAndContractCode(pageRequest,contractCode,nameCustomer);
        }
        if(!nameCustomer.equals("") && !nameProduct.equals("") && contractCode.equals("") && beginDate.equals("")){
            contractPage = iContractRepository.findAllContractByNameCustomerAndNameProduct(pageRequest,nameCustomer,nameProduct);
        }
        if(!contractCode.equals("") && !nameProduct.equals("") && nameCustomer.equals("") && beginDate.equals("")){
            contractPage = iContractRepository.findAllContractByContractCodeAndNameProduct(pageRequest,contractCode,nameProduct);
        }
        if(!nameCustomer.equals("") && !contractCode.equals("") && !beginDate.equals("") && (nameProduct.equals(""))){
            contractPage = iContractRepository.findAllContractByNameCustomerAndBeginDateAndContractCode(pageRequest,nameCustomer,contractCode,beginDate);
        }
        if(!(nameProduct.equals("")) && !contractCode.equals("") && !beginDate.equals("") && nameCustomer.equals("")){
            contractPage = iContractRepository.findAllContractByNameProductAndBeginDateAndContractCode(pageRequest,nameProduct,contractCode,beginDate);
        }
        if(!nameCustomer.equals("") && !nameProduct.equals("") && !beginDate.equals("") && contractCode.equals("")){
            contractPage = iContractRepository.findAllContractByNameCustomerAndBeginDateAndNameProduct(pageRequest,nameCustomer,nameProduct,beginDate);
        }
        if(!nameCustomer.equals("") && !contractCode.equals("") && !nameProduct.equals("") && beginDate.equals("")){
            contractPage = iContractRepository.findAllContractByNameCustomerAndNameProductAndContractCode(pageRequest,nameCustomer,contractCode,nameProduct);
        }
        if(nameCustomer.equals("") && contractCode.equals("") && nameProduct.equals("") && beginDate.equals("")){
            contractPage = iContractRepository.findAll(pageRequest);
        }
        if(!nameCustomer.equals("") && !contractCode.equals("") && !nameProduct.equals("") && !beginDate.equals("")){
            contractPage = iContractRepository.findAllContractWithPage(pageRequest,contractCode,nameCustomer,nameProduct,beginDate);
        }
        return contractPage;
    }
}
