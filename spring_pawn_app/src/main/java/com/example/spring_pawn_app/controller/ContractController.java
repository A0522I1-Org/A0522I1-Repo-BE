package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping("api/contracts")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class ContractController {
    @Autowired
    private IContractService iContractService;

    @Autowired
     private MailSender mailSender;

    /**
     * Created by: HoangVV,
     * Date create: 20/05/2023
     * Function: get all contract and search contract with contractCode, nameCustomer, nameProduct, beginDate
     *
     * @param page
     * @param contractCode
     * @param nameCustomer
     * @param nameProduct
     * @param beginDate
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     */
    @GetMapping("listSelect")
    public Page<Contract> findAllContractWithPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "contractCode", defaultValue = "") String contractCode,
                                                  @RequestParam(value = "nameCustomer", defaultValue = "") String nameCustomer,
                                                  @RequestParam(value = "nameProduct", defaultValue = "") String nameProduct,
                                                  @RequestParam(value = "beginDate", defaultValue = "") String beginDate) {
        Page<Contract> contractPage = iContractService.findAllContractWithPage(PageRequest.of(page, 5), contractCode, nameCustomer, nameProduct, beginDate);
        return contractPage;
    }

    /**
     * Created by: HoangVV,
     * Date create: 15/05/2023
     * Function: get contract with id
     *
     * @param id
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     */
    @GetMapping("/select/{id}")
    public ResponseEntity<ContractDto> findContractById(@PathVariable("id") Integer id) {
        if (iContractService.findContractById(id) != null) {
            return ResponseEntity.of(Optional.of(iContractService.findContractById(id)));
        }
        return ResponseEntity.ok(null);
    }

    /**
     * Created by: HoangVV
     * Date create: 15/05/2023
     * Function: update contract with id
     *
     * @param id
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     * @throws MessagingException
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> getContractService(@PathVariable("id") Integer id) throws MessagingException {
        ContractDto contractDto = iContractService.findContractById(id);
        if (contractDto.getStatus().getId() != 2) {
            iContractService.updateContractPayment(id);
            mailSender.sendEmailPay(iContractService.findContractById(id));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

     /** Create by PhongTD
     * Date created: 20/05/2023
     * @param pageable
     * @return page<Contract>
     */
    @GetMapping("")
    public Page<Contract> getPageContract(@PageableDefault(5) Pageable pageable) {
        return iContractService.findAll(pageable);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param id
     * @return ContractEditDto
     */
    @GetMapping("{id}")
    public ContractEditDto getContractById(@PathVariable("id") Integer id) {
        return iContractService.findById(id);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param id
     * @param contractEditDto
     * @return HttpStatus.Ok if contractEditDto not null. Else return HttpStatus.NOT_FOUND
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateContract(@PathVariable Integer id, @RequestBody ContractEditDto contractEditDto) {
        if (contractEditDto != null) {
            iContractService.edit(contractEditDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param id
     * @return HttpStatus.Ok if id not null. Else return HttpStatus.NO_CONTENT
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteContract(@PathVariable("id")Integer id) {
        if (id != null) {
            iContractService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param pageable
     * @return page<Contract>
     */
    @GetMapping("search")
    public Page<Contract> showListSearch(@RequestParam(name = "customerName", defaultValue = "") String customerName,
                                         @RequestParam(name = "productName", defaultValue = "") String productName,
                                         @RequestParam(name = "beforeDate", defaultValue = "") String beforeDate,
                                         @RequestParam(name = "afterDate", defaultValue = "") String afterDate,
                                         @RequestParam(name = "status", defaultValue = "") String status,
                                         @PageableDefault(5)Pageable pageable) {
        return iContractService.search(customerName, productName, beforeDate, afterDate, status, pageable);

    }

    @PostMapping("")
    public ResponseEntity<?> saveContract(@RequestBody ContractCreateDto contractDto){
        iContractService.saveContract(contractDto);
        try {
            mailSender.sendEmailCreate(contractDto);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

