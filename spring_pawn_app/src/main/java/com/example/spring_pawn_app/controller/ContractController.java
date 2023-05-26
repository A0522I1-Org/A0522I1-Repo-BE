package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.contract.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.service.contract.ContractService;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ContractController {
    @Autowired
    ContractService iContractService;

    @Autowired
    MailSender mailSender;

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
    @GetMapping("/contract")
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
//    @GetMapping("/contract/{id}")
//    public ContractDto findContractById(@PathVariable("id") Integer id){
//        return iContractService.findContractById(id);
//    }
    @GetMapping("/contract/{id}")
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
    @PutMapping("/contract/{id}")
    public ResponseEntity<?> getContractService(@PathVariable("id") Integer id) throws MessagingException {
        ContractDto contractDto = iContractService.findContractById(id);
        if (contractDto.getStatus().getId() != 3) {
            iContractService.updateContractPayment(id);
            mailSender.sendEmailPay(iContractService.findContractById(id));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/contract")
    public ResponseEntity<?> saveContract(@RequestBody ContractCreateDto contractDto) {
        iContractService.saveContract(contractDto);
        try {
            mailSender.sendEmailCreate(contractDto);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

