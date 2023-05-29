package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.service.contract.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/contracts")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class ContractController {
    @Autowired
    private IContractService iContractService;

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param pageable
     * @return page<Contract>
     */
    @GetMapping("")
    public Page<Contract> getPageContract(@PageableDefault(5)Pageable pageable) {
        return iContractService.findAll(pageable);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param id
     * @return ContractEditDto
     */
    @GetMapping("{id}")
    public ContractEditDto getContractById(@PathVariable("id") Integer id) {
        return iContractService.findById(id);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param id
     * @param contractEditDto
     * @return HttpStatus.Ok if contractEditDto not null. Else return HttpStatus.NOT_FOUND
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateContract(@PathVariable Integer id, @RequestBody ContractEditDto contractEditDto) {
        if (contractEditDto != null) {
            iContractService.edit(contractEditDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param id
     * @return HttpStatus.Ok if id not null. Else return HttpStatus.NO_CONTENT
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteContract(@PathVariable("id")Integer id) {
        if (id != null) {
            iContractService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create by PhongTD
     * Date created: 20/05/2023
     * @param pageable
     * @return page<Contract>
     */
    @GetMapping("search")
    public Page<Contract> showListSearch(@RequestParam(name = "customerName", defaultValue = "") String customerName,
                                         @RequestParam(name = "productName", defaultValue = "") String productName,
                                         @RequestParam(name = "beforeDate", defaultValue = "") String beforeDate,
                                         @RequestParam(name = "afterDate", defaultValue = "") String afterDate,
                                         @RequestParam(name = "status", defaultValue = "") String status,
                                         @PageableDefault(5)Pageable pageable) {
        return iContractService.search(customerName, productName, beforeDate, afterDate, status, pageable);
    }
}
