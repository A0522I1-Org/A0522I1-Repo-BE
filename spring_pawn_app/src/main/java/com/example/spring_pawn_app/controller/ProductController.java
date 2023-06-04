package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.service.product.IProductService;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring_pawn_app.dto.contract.ContractProductDTO;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.service.contract.IContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IContractService iContractService;

    @Autowired
    private IProductService iProductService;
    /**
     * genarate 13May2023
     * TinPNT
     * @return List of contract with status in (1,2) and can find with customer name and category id of product
     */
    @GetMapping("")
    public ResponseEntity<Page<Contract>> findAllContractNotPay(
            @RequestParam(value = "namecustomer", defaultValue = "") String nameCustomer,
            @RequestParam(value = "categoryname", defaultValue = "") String categoryName,
            @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Contract> contractPage = iContractService.findAllProductNotPay(pageable, nameCustomer, categoryName);
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
    @GetMapping("/{id}")
    public ResponseEntity<ContractProductDTO> getProduct(@PathVariable("id") int id) {
        Contract contract = iContractService.findContractNotPayById(id);
        ContractProductDTO contractDTO = new ContractProductDTO();
        BeanUtils.copyProperties(contract, contractDTO);
        if (contractDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractDTO, HttpStatus.OK);
    }

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @GetMapping("/customer/{id}")
    public List<Product> findProductByCustomer(@PathVariable("id") Integer id) {
        return iProductService.findAllByCustomer( id );
    }

}
