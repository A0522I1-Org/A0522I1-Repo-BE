package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class ContractController {
    @Autowired
    IContractService iContractService;

    @Autowired
    MailSender mailSender;

    @PostMapping("/contract")
    public ResponseEntity<?> saveContract(@RequestBody ContractCreateDto contractDto){
        iContractService.saveContract(contractDto);
        try {
            mailSender.sendEmailPay(contractDto);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
