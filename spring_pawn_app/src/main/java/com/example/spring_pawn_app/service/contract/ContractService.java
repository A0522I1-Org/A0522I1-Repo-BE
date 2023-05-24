package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.repository.employee.IEmployeeRepository;
import com.example.spring_pawn_app.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class ContractService implements IContractService {
    @Autowired
    IContractRepository iContractRepository;
    @Autowired
    IEmployeeRepository iEmployeeRepository;
    @Autowired
    IProductRepository iProductRepository;

    private String generateContractCode() {
        int codeLength = 4; // Độ dài của mã xác thực
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Các ký tự có thể sử dụng trong mã xác thực
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    @Override
    public void saveContract(ContractCreateDto contractDto) {
        Contract contract = new Contract();
        Product product = new Product(contractDto.getNameProduct(), contractDto.getPrice(), contractDto.getCategory());

        iProductRepository.save(product);

        Employee employee = iEmployeeRepository.findEmployeeByUserName(contractDto.getUsername());

        contract.setContractCode("CT-"+this.generateContractCode());
        contract.setBeginDate(contractDto.getBeginDate());
        contract.setEndDate(contractDto.getEndDate());
        contract.setCustomer(contractDto.getCustomer());
        contract.setInterest(contractDto.getInterest());
        contract.setStatus(contractDto.getStatus());
        contract.setProduct(product);
        contract.setEmployee(employee);
        iContractRepository.save(contract);
    }
}
