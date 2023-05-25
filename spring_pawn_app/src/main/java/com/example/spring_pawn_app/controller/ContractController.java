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

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ContractController {
    @Autowired
    IContractService iContractService;

    @GetMapping("/contracts/{id}")
    public ContractDto findContractById(@PathVariable("id") Integer id) throws MessagingException {
       iContractService.findContractById(id);
        return iContractService.findContractById(id);
    }
    ///// customer có nhìu món đồ
    @GetMapping("/contracts")
    public ResponseEntity<Page<Contract>> findAll(@RequestParam(value = "customer_id",defaultValue = "") Integer customer_id,
                                                  @RequestParam(defaultValue = "0") int page) {
        Page<Contract> contractPage = iContractService.findContractByCustomerId( PageRequest.of( page,5 ),customer_id );
        if (contractPage == null){
            return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Page<Contract>>( contractPage,HttpStatus.OK);
    }

    //// thay đổi trạng thái status
    @PutMapping("/contracts/{id}")
    public ResponseEntity<?> getiContractService(@PathVariable("id")Integer id){
        iContractService.updateContractLiquidation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
