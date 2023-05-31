package com.example.spring_pawn_app.service.contract;


import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.service.customer.CustomerService;
import com.example.spring_pawn_app.service.employee.EmployeeService;
import com.example.spring_pawn_app.service.img.ImgService;
import com.example.spring_pawn_app.service.product.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository iContractRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ImgService imgService;

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
    public ContractDto findContractById(Integer id) {
        ContractDto contractDto = new ContractDto();
        BeanUtils.copyProperties(iContractRepository.findContractById(id),contractDto);
        return contractDto;
    }

    @Override
    public void updateContractPayment(Integer id) {
        iContractRepository.updateContractPayment(id);
    }

    @Override
    public void saveContract(ContractCreateDto contractDto) {
        Contract contract = new Contract();
        Product product = new Product(contractDto.getNameProduct(), contractDto.getPrice(), contractDto.getCategory());
        Img img = new Img(contractDto.getImgPath(), product);

        productService.save(product);
        imgService.saveImg(img);

        Employee employee = employeeService.findEmployeeByUserName(contractDto.getUsername());

        contract.setContractCode("CT-" + this.generateContractCode());
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

    @Override
    public Page<Contract> findAllProductNotPay(Pageable page, String nameCustomer, String categoryId) {
        return iContractRepository.findAllProductNotPay(nameCustomer, categoryId, page);
    }

    @Override
    public Contract findContractNotPayById(int id) {
        return iContractRepository.findContractNotPayByID(id);
    }
}

