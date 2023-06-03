package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.customer.ICustomerService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContractController {
    @Autowired
    private IContractService iContractService;

    @GetMapping("/contracts/{id}")
    public ContractDto findContractById(@PathVariable("id") Integer id) throws MessagingException {
       iContractService.findContractById(id);
        return iContractService.findContractById(id);
    }

    @PutMapping("/contract/{id}")
    public ResponseEntity<?> updateContract(@PathVariable Integer id){
        iContractService.updateContractLiquidation( id );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
