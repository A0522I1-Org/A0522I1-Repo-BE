package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface IContractService {
    void edit(ContractEditDto contractEditDto);
    ContractEditDto findById(Integer id);
    void delete(Integer id);
    Page<Contract> findAll(Pageable pageable);

    Page<Contract> search(String customerName, String productName, String beforeDate,
                          String afterDate, String status, Pageable pageable);
    void saveContract(ContractCreateDto contractDto);
}
