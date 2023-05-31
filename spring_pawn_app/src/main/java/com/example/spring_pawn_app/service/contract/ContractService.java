package com.example.spring_pawn_app.service.contract;


import com.example.spring_pawn_app.dto.contract.ContractCreateDto;
import com.example.spring_pawn_app.model.Contract;

import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import com.example.spring_pawn_app.service.employee.IEmployeeService;
import com.example.spring_pawn_app.service.img.IImgService;
import com.example.spring_pawn_app.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.model.Product;

import java.security.SecureRandom;


@Service
public class ContractService implements IContractService {
    @Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    IContractRepository iContractRepository;


    @Autowired
    private IProductService productService;
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IImgService imgService;

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
        Img img = new Img(contractDto.getImgPath(), product);

        productService.save(product);
        imgService.saveImg(img);

        Employee employee = iEmployeeService.findEmployeeByUserName(contractDto.getUsername());
        contract.setContractCode("HD-" + this.generateContractCode());
        contract.setBeginDate(contractDto.getBeginDate());
        contract.setEndDate(contractDto.getEndDate());
        contract.setCustomer(customerService.findCustomerById(contractDto.getCustomer().getId()));
        contract.setInterest(contractDto.getInterest());
        contract.setStatus(contractDto.getStatus());
        contract.setProduct(product);
        contract.setEmployee(employee);
        iContractRepository.save(contract);


    }

    @Override
    public Page<Contract> findAllProductNotPay(Pageable page, String nameCustomer, String categoryName) {
        return iContractRepository.findAllProductNotPay(nameCustomer, categoryName, page);
    }

    @Override
    public Contract findById(int id) {
        return iContractRepository.findContractNotPayByID(id);
    }
}


