package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/employee/{username}")
    public Employee findEmployeeByUserName(@PathVariable("username") String username){
        return employeeService.findEmployeeByUserName(username);
    }
}
