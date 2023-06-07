package com.example.spring_pawn_app.service.customer;

import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import com.example.spring_pawn_app.mapper.CustomerMapper;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.spring_pawn_app.dto.customer.CustomerDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository iCustomerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Page<Customer> findByCustomer(String customer_name, PageRequest page) {
        return iCustomerRepository.findByCustomer(customer_name, page);
    }

    @Override
    public Customer findCustomerById(Integer idCustomer) {
        return iCustomerRepository.findCustomerById(idCustomer);
    }

    @Override
    public Page<CustomerListDto> findAllCustomerWithPage(PageRequest pageRequest, String nameCustomer) {
        return iCustomerRepository.findAllCustomerWithPage(pageRequest, nameCustomer);
    }

    /**
     * Create by: ManPD
     * Date create: 21/5/2023
     *
     * @param customer
     */
    @Override
    public Customer createCustomer(Customer customer) {
        return iCustomerRepository.save(customer);
    }

    /**
     * Create by: ManPD
     * Date create: 6/6/2023
     *
     * @return String
     */
    @Override
    public String createCustomerCode() {
        Random random = new Random();
        int customerIdInt;
        String customerIdStr;
        String customerCodeRamDum = "KH-";
        do {
            customerIdInt = random.nextInt(100000000);
            customerIdStr = String.format("%08d", customerIdInt);
            customerCodeRamDum = customerCodeRamDum + customerIdStr;
        } while (this.getCustomerByCustomerCode(customerCodeRamDum) != null);
        return customerCodeRamDum;
    }

    /**
     * Create by: ManPD
     * Date create: 6/6/2023
     *
     * @return customer
     */
    @Override
    public Customer getCustomerByCustomerCode(String customerCode) {
        return iCustomerRepository.findCustomerByCustomerCode(customerCode);
    }

    /**
     * @author Trần Thế Huy
     * @version 2
     * @since 6/6/2023
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTODetail> getCustomerById(Integer id) {
        List<Object[]> results = this.iCustomerRepository.getCustomerById(id);
        return results.stream()
                .findFirst()
                .map(customerMapper::mapToDtoDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTODetail> getCustomerByIdInRestore(Integer id) {
        List<Object[]> results = this.iCustomerRepository.getCustomerByIdInRestore(id);
        return results.stream()
                .findFirst()
                .map(customerMapper::mapToDtoDetail);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        this.iCustomerRepository.deleteCustomerById(id);
    }

    @Override
    public void restoreCustomerById(Integer id) {
        this.iCustomerRepository.restoreCustomerById(id);
    }

    @Override
    public Page<CustomerDTOList> getAllWithRequirement(String valueReceived, LocalDate searchDateOfBirth, Integer searchGender, Pageable pageable) {
        Page<Object[]> resultPage = this.iCustomerRepository.getAllWithRequirement(valueReceived, searchDateOfBirth, searchGender, pageable);
        List<CustomerDTOList> customerDtoList = resultPage.getContent()
                .stream()
                .map(customerMapper::mapToDtoList)
                .collect(Collectors.toList());
        return new PageImpl<>(customerDtoList, pageable, resultPage.getTotalElements());
    }

    @Override
    public Page<CustomerDTORestore> getAllWithRequirementInRestore(String valueReceived, Pageable pageable) {
        Page<Object[]> resultPage = this.iCustomerRepository.getAllWithRequirementInRestore(valueReceived, pageable);
        List<CustomerDTORestore> customerDtoRestore = resultPage.getContent()
                .stream()
                .map(customerMapper::mapToDtoRestore)
                .collect(Collectors.toList());
        return new PageImpl<>(customerDtoRestore, pageable, resultPage.getTotalElements());
    }


    @Override
    public CustomerDTO addNewCustomer(CustomerDTO customer) {
        iCustomerRepository.createCustomer(customer);
        return customer;
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        // Kiểm tra xem khách hàng có tồn tại trong cơ sở dữ liệu hay không
        Optional<CustomerDTODetail> existingCustomer = getCustomerById(customer.getId());
        if (existingCustomer.isPresent()) {
            // Cập nhật thông tin khách hàng
            CustomerDTODetail customerToUpdate = existingCustomer.get();
            customerToUpdate.setCustomerCode(customer.getCustomerCode());
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setGender(customer.getGender());
            customerToUpdate.setDateOfBirth(customer.getDateOfBirth());
            customerToUpdate.setIdentityCard(customer.getIdentityCard());
            customerToUpdate.setPhone(customer.getPhone());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAddress(customer.getAddress());
            customerToUpdate.setAvatar(customer.getAvatar());

            // Lưu thông tin khách hàng đã được cập nhật vào cơ sở dữ liệu
            iCustomerRepository.updateCustomer(customer);
        }
    }

    @Override
    public String existsByCustomerEmail(String email) {
        return iCustomerRepository.existsByCustomerEmail(email);
    }

    @Override
    public String existsByCustomerPhone(String phone) {
        return iCustomerRepository.existsByCustomerPhone(phone);
    }

    @Override
    public String existsByCustomerIdentityCard(String idCard) {
        return iCustomerRepository.existsByCustomerIdentityCard(idCard);
    }

    @Override
    public String existsByCustomerCode(String code) {
        return iCustomerRepository.existsByCustomerCode(code);
    }
}
