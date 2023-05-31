package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.FinaceDTO;
import com.example.spring_pawn_app.service.Finace.IFinaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class FinaceController {
    @Autowired
    private IFinaceService iFinaceService;

    /**
     * genarate 13May2023
     * TinPNT
     * @return Finance of pawnshop
     */
    @GetMapping("finace")
    public ResponseEntity<FinaceDTO> getFinaceDTO(){
        if (iFinaceService.getFinaction()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<FinaceDTO>(iFinaceService.getFinaction(), HttpStatus.OK);
    }
}
