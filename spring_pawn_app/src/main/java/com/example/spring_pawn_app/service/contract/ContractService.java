package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import com.example.spring_pawn_app.repository.product.IProductRepository;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import com.example.spring_pawn_app.service.employee.IEmployeeService;
import com.example.spring_pawn_app.service.product.IProductService;
import org.springframework.beans.BeanUtils;

import com.example.spring_pawn_app.dto.contract.ContractCreateDto;

import com.example.spring_pawn_app.service.img.IImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class ContractService implements IContractService {


    @Autowired
    ICustomerService customerService;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IContractRepository iContractRepository;

    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IProductService productService;
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

    /**
     * Created By: HoangVV
     * @param id
     * @return
     */
    @Override
    public ContractDto findContractById(Integer id) {
        ContractDto contractDto = new ContractDto();
        BeanUtils.copyProperties(iContractRepository.findContractById(id), contractDto);
        return contractDto;
    }

    /**
     * Created By: HoangVV
     * @param id
     */
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

        Employee employee = iEmployeeService.findEmployeeByUserName(contractDto.getUsername());

        contract.setContractCode("CT-" + this.generateContractCode());
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

    /**
     * Created By:HoangVV
     * @param pageRequest
     * @param contractCode
     * @param nameCustomer
     * @param nameProduct
     * @param beginDate
     * @return
     */
    @Override
    public Page<Contract> findAllContractWithPage(PageRequest pageRequest, String contractCode, String nameCustomer, String nameProduct, String beginDate) {
        Page<Contract> contractPage = null;
        if (!contractCode.equals("") && nameCustomer.equals("") && nameProduct.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractByContractCodeWithPage(pageRequest, contractCode);
        }
        if (!nameCustomer.isEmpty() && contractCode.isEmpty() && nameProduct.isEmpty() && beginDate.isEmpty()) {
            contractPage = iContractRepository.findAllContractByNameCustomerWithPage(pageRequest, nameCustomer);
        }
        if (!nameProduct.equals("") && nameCustomer.equals("") && contractCode.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractByNameProductWithPage(pageRequest, nameProduct);
        }
        if (!beginDate.equals("") && nameCustomer.equals("") && contractCode.equals("") && nameProduct.equals("")) {
            contractPage = iContractRepository.findAllContractByBeginDateWithPage(pageRequest, beginDate);
        }
        if (!beginDate.equals("") && !nameCustomer.equals("") && contractCode.equals("") && nameProduct.equals("")) {
            contractPage = iContractRepository.findAllContractByNameCustomerAndBeginDate(pageRequest, nameCustomer, beginDate);
        }
        if (!beginDate.equals("") && !contractCode.equals("") && nameCustomer.equals("") && nameProduct.equals("")) {
            contractPage = iContractRepository.findAllContractByContractCodeAndBeginDate(pageRequest, contractCode, beginDate);
        }
        if (!beginDate.equals("") && !nameProduct.equals("") && nameCustomer.equals("") && contractCode.equals("")) {
            contractPage = iContractRepository.findAllContractByNameProductAndBeginDate(pageRequest, nameProduct, beginDate);
        }
        if (!nameCustomer.equals("") && !contractCode.equals("") && nameProduct.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractByNameCustomerAndContractCode(pageRequest, contractCode, nameCustomer);
        }
        if (!nameCustomer.equals("") && !nameProduct.equals("") && contractCode.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractByNameCustomerAndNameProduct(pageRequest, nameCustomer, nameProduct);
        }
        if (!contractCode.equals("") && !nameProduct.equals("") && nameCustomer.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractByContractCodeAndNameProduct(pageRequest, contractCode, nameProduct);
        }
        if (!nameCustomer.equals("") && !contractCode.equals("") && !beginDate.equals("") && (nameProduct.equals(""))) {
            contractPage = iContractRepository.findAllContractByNameCustomerAndBeginDateAndContractCode(pageRequest, nameCustomer, contractCode, beginDate);
        }
        if (!(nameProduct.equals("")) && !contractCode.equals("") && !beginDate.equals("") && nameCustomer.equals("")) {
            contractPage = iContractRepository.findAllContractByNameProductAndBeginDateAndContractCode(pageRequest, nameProduct, contractCode, beginDate);
        }
        if (!nameCustomer.equals("") && !nameProduct.equals("") && !beginDate.equals("") && contractCode.equals("")) {
            contractPage = iContractRepository.findAllContractByNameCustomerAndBeginDateAndNameProduct(pageRequest, nameCustomer, nameProduct, beginDate);
        }
        if (!nameCustomer.equals("") && !contractCode.equals("") && !nameProduct.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractByNameCustomerAndNameProductAndContractCode(pageRequest, nameCustomer, contractCode, nameProduct);
        }
        if (nameCustomer.equals("") && contractCode.equals("") && nameProduct.equals("") && beginDate.equals("")) {
            contractPage = iContractRepository.findAllContract(pageRequest);
        }
        if (!nameCustomer.equals("") && !contractCode.equals("") && !nameProduct.equals("") && !beginDate.equals("")) {
            contractPage = iContractRepository.findAllContractWithPage(pageRequest, contractCode, nameCustomer, nameProduct, beginDate);
        }
        return contractPage;
    }

    @Override
    public Page<Contract> findAllProductNotPay(Pageable page, String nameCustomer, String categoryName) {
        return iContractRepository.findAllProductNotPay(nameCustomer, categoryName, page);
    }

    @Override
    public Contract findContractNotPayById(int id) {
        return iContractRepository.findContractNotPayByID(id);
    }

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     *
     * @param contractEditDto
     */
    @Override
    public void edit(ContractEditDto contractEditDto) {
        iContractRepository.updateContract(contractEditDto.getBeginDate(), contractEditDto.getEndDate(),
                contractEditDto.getStatus(), contractEditDto.getId());
        iCustomerRepository.updateCustomerName(contractEditDto.getCustomerName(), contractEditDto.getCustomerId());
        iProductRepository.updateFromContractEdit(contractEditDto.getProductName(), contractEditDto.getCategory(), contractEditDto.getProductId());
    }

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     *
     * @param id
     * @return ContractEditDto was converted from contract
     */
    @Override
    public ContractEditDto findById(Integer id) {
        Contract contract = iContractRepository.findAllById(id);
        ContractEditDto contractEditDto = new ContractEditDto(
                contract.getId(),
                contract.getContractCode(),
                contract.getCustomer().getName(),
                contract.getCustomer().getId(),
                contract.getProduct().getName(),
                contract.getProduct().getId(),
                contract.getBeginDate(),
                contract.getEndDate(),
                contract.getProduct().getCategory(),
                contract.getStatus());
        return contractEditDto;
    }

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     *
     * @param id must not be {@literal null}.
     */
    @Override
    public void delete(Integer id) {
        iContractRepository.deleteById(id);
    }

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     *
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *                 {@literal null}.
     * @return Page<Contract>
     */
    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return iContractRepository.findAll(pageable);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     *
     * @param customerName
     * @param productName
     * @param beforeDate
     * @param afterDate
     * @param status
     * @param pageable
     * @return page<Contract> was searched by customerName, productName, beginDate and status
     */
    @Override
    public Page<Contract> search(String customerName, String productName, String beforeDate,
                                 String afterDate, String status, Pageable pageable) {
        if (!customerName.equals("") && productName.equals("") && beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            return iContractRepository.findByCustomerName(customerName, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            return iContractRepository.findByProductName(productName, pageable);
        }

        if (customerName.equals("") && productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByBeforeDate(bd, pageable);
        }

        if (customerName.equals("") && productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByAfterDate(ad, pageable);
        }

        if (customerName.equals("") && productName.equals("") && beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            return iContractRepository.findByStatus(status, pageable);
        }
        if (!customerName.equals("") && !productName.equals("") && beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            return iContractRepository.findByCustomerAndProduct(customerName, productName, pageable);
        }

        if (!customerName.equals("") && productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndBeforeDate(customerName, bd, pageable);
        }

        if (!customerName.equals("") && productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndAfterDate(customerName, ad, pageable);
        }

        if (!customerName.equals("") && productName.equals("") && beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            return iContractRepository.findByCustomerAndStatus(customerName, status, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByProductAndBeforeDate(productName, bd, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByProductAndAfterDate(productName, ad, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            return iContractRepository.findByProductAndStatus(productName, status, pageable);
        }

        if (customerName.equals("") && productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByBeginDateBetween(bd, ad, pageable);
        }

        if (customerName.equals("") && productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByBeforeDateAndStatus(bd, status, pageable);
        }

        if (customerName.equals("") && productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByAfterDateAndStatus(ad, status, pageable);
        }
        if (!customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndProductAndBeforeDate(customerName, productName, bd, pageable);
        }

        if (!customerName.equals("") && !productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndProductAndAfterDate(customerName, productName, ad, pageable);
        }

        if (!customerName.equals("") && !productName.equals("") && beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            return iContractRepository.findByCustomerAndProductAndStatus(customerName, productName, status, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByProductAndBAndBeginDate(productName, bd, ad, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByProductAndBAndBeforeDateAndStatus(productName, bd, status, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByProductAndBAndAfterDateAndStatus(productName, ad, status, pageable);
        }

        if (customerName.equals("") && productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByStatusAndBAndBeginDate(status, bd, ad, pageable);
        }
        if (!customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndProductAndBeginDate(customerName, productName, bd, ad, pageable);
        }

        if (!customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndProductAndBeforeDateAndStatus(customerName, productName, bd, status, pageable);
        }

        if (customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByProductAndBeginDateAndStatus(productName, bd, ad, status, pageable);
        }

        if (!customerName.equals("") && productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndBeginDateAndStatus(customerName, bd, ad, status, pageable);
        }

        if (!customerName.equals("") && !productName.equals("") && beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndProductAndAfterDateAndStatus(customerName, productName, ad, status, pageable);
        }
        if (!customerName.equals("") && !productName.equals("") && !beforeDate.equals("") && !afterDate.equals("") && !status.equals("")) {
            LocalDate bd = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate ad = LocalDate.parse(afterDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return iContractRepository.findByCustomerAndProductAndBeginDateAndStatus(customerName, productName, bd, ad, status, pageable);
        }
        return iContractRepository.findAll(pageable);
    }

    public Contract findById(int id) {
        return iContractRepository.findContractNotPayByID(id);
    }
}


