package com.example.spring_pawn_app.controller;


import com.example.spring_pawn_app.dto.EmployeeInforDTO;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.service.employee.EmployeeService;
import com.example.spring_pawn_app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeInforDTO> findByIdEmployee(@PathVariable Integer id){
       EmployeeInforDTO  employeeInforDTO= new EmployeeInforDTO();
        Employee employee=employeeService.finById(id);
        User user=userService.findByIdEmployee(id);
        employeeInforDTO.setId(employee.getId().toString());
        employeeInforDTO.setAddress(employee.getAddress());
        employeeInforDTO.setAvt(employee.getAvt());
        employeeInforDTO.setEmail(employee.getEmail());
        employeeInforDTO.setDateOfBirth(employee.getDateOfBirth().toString());
        employeeInforDTO.setGender(employee.getGender().toString());
        employeeInforDTO.setId_cart(employee.getId_cart());
        employeeInforDTO.setName(employee.getName());
        employeeInforDTO.setPhone(employee.getPhone());
        employeeInforDTO.setUserName(user.getUserName());
        employeeInforDTO.setPassword(user.getPassword());
        employeeInforDTO.setConfirmPassword(user.getPassword());
        Optional<EmployeeInforDTO> dto= Optional.of(employeeInforDTO);
        return  ResponseEntity.of(dto);
    }
    @PutMapping(value = "/employee/save")
    public ResponseEntity<EmployeeInforDTO> update(@RequestBody EmployeeInforDTO employeeInforDTO) {
        System.out.println(employeeInforDTO);
       User user=userService.findByIdEmployee(Integer.parseInt(employeeInforDTO.getId()));
       user=new User(user.getId(),employeeInforDTO.getUserName(), employeeInforDTO.getPassword(), user.getEmployee(),user.isFlag());
       userService.save(user);
       employeeService.save(new Employee(Integer.parseInt(employeeInforDTO.getId()),employeeInforDTO.getName(), LocalDate.parse(employeeInforDTO.getDateOfBirth()),employeeInforDTO.getPhone(),employeeInforDTO.getEmail(),Integer.parseInt(employeeInforDTO.getGender()),employeeInforDTO.getAddress(),employeeInforDTO.getId_cart(),false,employeeInforDTO.getAvt()));
        return new ResponseEntity<>(HttpStatus.OK);
}
}
