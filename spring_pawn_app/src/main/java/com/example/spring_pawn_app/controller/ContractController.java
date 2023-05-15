package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ContractController {
    @Autowired
    IContractService iContractService;

    @Autowired
    MailSender mailSender;
    @GetMapping("/contract")
    public Page<Contract> findAllContractWithPage(@RequestParam(value = "page",defaultValue = "0")int page,
                                                     @RequestParam(value = "contractCode",defaultValue = "HD-001")String contractCode,
                                                     @RequestParam(value = "nameCustomer",defaultValue = "Hoang")String nameCustomer,
                                                     @RequestParam(value = "nameProduct",defaultValue = "AirBlad")String nameProduct,
                                                     @RequestParam(value = "beginDate",defaultValue = "")LocalDate beginDate){
        Page<Contract> contractDtoPage = iContractService.findAllContractWithPage(PageRequest.of(page,1),contractCode,nameCustomer,nameProduct,beginDate);
        return contractDtoPage;
    }
    @GetMapping("/contract/{id}")
    public ContractDto findContractById(@PathVariable("id") Integer id){
        return iContractService.findContractById(id);
    }

    @PutMapping("/contract/{id}")
    public ResponseEntity<?> getiContractService(@PathVariable("id")Integer id) throws MessagingException {
        iContractService.updateContractPayment(id);
        mailSender.sendEmailPay(iContractService.findContractById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
