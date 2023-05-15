package com.example.spring_pawn_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @GetMapping("/test")
    public ResponseEntity<String> test(){

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
