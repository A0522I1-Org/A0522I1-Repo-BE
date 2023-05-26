package com.example.spring_pawn_app.controller;


import com.example.spring_pawn_app.DTO.ContractDTO;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.service.contract.IContractService;
import com.example.spring_pawn_app.service.img.IImgService;
import com.example.spring_pawn_app.service.product.IProdcutService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class ProductController {
    @Autowired
    private IProdcutService iProdcutService;
    @Autowired
    private IContractService iContractService;
    /**
     * genarate 13May2023
     * TinPNT
     * @return List of contract with status in (1,2) and can find with customer name and category id of product
     */
    @GetMapping("products")
    public ResponseEntity<Page<Contract>> findAllContractNotPay(
            @RequestParam(value = "namecustomer", defaultValue = "") String nameCustomer,
            @RequestParam(value = "categoryid", defaultValue = "") String categoryId,
            @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Contract> contractPage = iContractService.findAllProductNotPay(pageable, nameCustomer, categoryId);
        if (contractPage.getContent() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       return new ResponseEntity<>(contractPage,HttpStatus.OK);
    }


    /**
     * genarate 13May2023
     * TinPNT
     * @return contract with status in (1,2) by ID of contract
     */
    @GetMapping("products/{id}")
    public ResponseEntity<ContractDTO> getProduct(@PathVariable("id") int id) {
        Contract contract = iContractService.findById(id);
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(contract, contractDTO);
        if (contractDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractDTO, HttpStatus.OK);
    }


}
