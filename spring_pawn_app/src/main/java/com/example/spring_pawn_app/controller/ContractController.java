package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractCreateDto;
import com.example.spring_pawn_app.service.contract.ContractService;
import com.example.spring_pawn_app.service.mail_sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ContractController {
    @Autowired
    ContractService contractService;
    @Autowired
    MailSender mailSender;

    /**
     * Create by ThuongVTH
     * Date create: 02/06/2023
     * @param contractDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/contracts")
    public ResponseEntity<?> saveContract(@Validated @RequestBody ContractCreateDto contractDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            // Xử lí thông tin lỗi và truyền cho Angular
            return ResponseEntity.badRequest().body(errors);
        } else {
            contractService.saveContract(contractDto);
            try {
                mailSender.sendEmailCreate(contractDto);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
