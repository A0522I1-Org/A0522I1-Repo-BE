package com.example.spring_pawn_app.service.contract;

import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import com.example.spring_pawn_app.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class ContractService implements IContractService{
    @Autowired
    private IContractRepository iContractRepository;

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Autowired
    private IProductRepository iProductRepository;

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
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
     * @param id must not be {@literal null}.
     */
    @Override
    public void delete(Integer id) {
        iContractRepository.deleteById(id);
    }

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *          {@literal null}.
     * @return Page<Contract>
     */
    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return iContractRepository.findAll(pageable);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
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
}
