package com.example.spring_pawn_app.service.Finace;

import com.example.spring_pawn_app.DTO.FinaceDTO;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFinace implements IFinaceService {
    @Autowired
    private IContractRepository iContractRepository;

    @Override
    public FinaceDTO getFinaction() {
        Double totalInterest = getAllInterest();
        Double totalInvestment = getAllPrice();
        Double currentFinace = (1000000000 + totalInterest) - totalInvestment;
        Double totalFinace = currentFinace  + totalInvestment;
        Double totalExpectedInterest = getExpectedInterest();
        FinaceDTO finaction = new FinaceDTO();
        System.out.println(totalInterest);
        System.out.println(totalInvestment);
        finaction.setTotalFinace(totalFinace);  
        finaction.setCurrentFinace(currentFinace);
        finaction.setInvestment(totalInvestment);
        finaction.setInterest(totalExpectedInterest);
        return finaction;
    }

    @Override
    public Double getAllInterest() {
        double totalInterest = 0;
        for (Contract contract : iContractRepository.findAllCurrentInterest()) {
                totalInterest += contract.getInterest();
        }
        return totalInterest;
    }
    @Override
    public Double getAllPrice() {
        double totalPriceProduct = 0;
        for (Contract contract : iContractRepository.findAll()) {
            if(contract.getStatus().getId()==1||contract.getStatus().getId() == 2) {
                totalPriceProduct += contract.getProduct().getPrice();
            }
        }
        return totalPriceProduct;
    }

    @Override
    public Double getExpectedInterest() {
        double totalExpectedInterest = 0;
        for (Contract contract : iContractRepository.findAll()) {
            if(contract.getStatus().getId()==1||contract.getStatus().getId() == 2) {
                totalExpectedInterest += contract.getInterest();
            }
        }
        return totalExpectedInterest;
    }
}
