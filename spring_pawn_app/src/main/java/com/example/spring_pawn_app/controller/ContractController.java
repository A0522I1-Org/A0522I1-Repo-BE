package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class ContractController {
    @Autowired
    IContractService iContractService;

    @Autowired
    MailSender mailSender;
    @GetMapping("/contract/{id}")
    public ContractDto findContractById(@PathVariable("id") Integer id) {
        return iContractService.findContractById(id);
    }

    @PutMapping("/contract/{id}")
    public ResponseEntity<?> updateContract(@PathVariable("id")Integer id) throws MessagingException {
        iContractService.updateContractPayment(id);
        mailSender.sendEmailPay(iContractService.findContractById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/contract")
    public ResponseEntity<?> saveContract(@RequestBody ContractDto contractDto){
        iContractService.saveContract(contractDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
