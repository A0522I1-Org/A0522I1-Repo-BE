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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
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
    @GetMapping("products")
    public ResponseEntity<List<ContractDTO>> findAllContractNotPay(
                                                                   @RequestParam(defaultValue = "")String nameCustomer,
                                                                   @RequestParam(defaultValue = "")String categoryId,
                                                                   @RequestParam(defaultValue = "0") int page){
        Page<Contract> contractPage = iContractService.findAllProductNotPay(PageRequest.of(page,5),nameCustomer,categoryId);
        List<ContractDTO> contractDTOList = new ArrayList<>();
        for (Contract contract : contractPage) {
            contractDTOList = new ArrayList<>();
            ContractDTO contractDTO = new ContractDTO();
            BeanUtils.copyProperties(contract, contractDTO);
            contractDTOList.add(contractDTO);
        }
        if (contractDTOList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractDTOList, HttpStatus.OK);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ContractDTO> getContract(@PathVariable("id") int id){
        Contract contract = iContractService.findById(id);
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(contract, contractDTO);
        if(contractDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractDTO,HttpStatus.OK);
    }


}
