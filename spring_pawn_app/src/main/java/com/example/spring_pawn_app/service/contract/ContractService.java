package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService implements IContractService{
    @Autowired
    IContractRepository iContractRepository;

    @Autowired
    private ICustomerRepository iCustomerRepository;



    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @Override
    public void updateContractLiquidation(Integer id) {
            iContractRepository.updateContractLiquidation(id);
    }

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @Override
    public List<Contract> findContractByCustomerId(Integer id) {
        Customer customer = iCustomerRepository.findCustomerById(id);
        return iContractRepository.findContractByCustomerId(id );
    }


}
