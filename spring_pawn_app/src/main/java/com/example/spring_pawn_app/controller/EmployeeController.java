package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.service.employee.IEmployeeService;
import com.example.spring_pawn_app.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.spring_pawn_app.dto.custom_error.InvalidDataException;
import com.example.spring_pawn_app.dto.custom_error.ValidationError;
import com.example.spring_pawn_app.dto.employee.EmployeeInforDTO;
import com.example.spring_pawn_app.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    /**
     * Create by ThuongVTH
     * Date create: 02/06/2023
     * @param username
     * @return
     */
    @GetMapping("/employee/{username}")
    public Employee findEmployeeByUserName(@PathVariable("username") String username) {
        return iEmployeeService.findEmployeeByUserName(username);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeInforDTO> findByIdEmployee(@PathVariable Integer id) {
        EmployeeInforDTO employeeInforDTO = new EmployeeInforDTO();
        Employee employee = iEmployeeService.finById(id);
        User user = iUserService.findByIdEmployee(id);
        BeanUtils.copyProperties(employee, employeeInforDTO);
        employeeInforDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeInforDTO.setUserName(user.getUserName());
        employeeInforDTO.setPassword(user.getPassword());
        employeeInforDTO.setConfirmPassword(user.getPassword());
        Optional<EmployeeInforDTO> dto = Optional.of(employeeInforDTO);
        return ResponseEntity.of(dto);
    }

    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: update employee information
     *
     * @param employeeInforDTO
     * @param bindingResult
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     */
    @PutMapping(value = "/employee/save")
    public ResponseEntity<?> updateEmployeeInfor(@Valid @RequestBody EmployeeInforDTO
                                                         employeeInforDTO, BindingResult bindingResult) {
        new EmployeeInforDTO().validate(employeeInforDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> {
                        String fieldName = error.getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    });
            return ResponseEntity.badRequest().body(errors);
        }
        User user = iUserService.findByIdEmployee(employeeInforDTO.getId());
        user = new User(user.getId(), employeeInforDTO.getUserName(), employeeInforDTO.getPassword(), user.getEmployee(), user.isFlag());
        iUserService.save(user);
        iEmployeeService.save(new Employee(employeeInforDTO.getId(), employeeInforDTO.getName(), employeeInforDTO.getDateOfBirth(), employeeInforDTO.getPhone(), employeeInforDTO.getEmail(), employeeInforDTO.getGender(), employeeInforDTO.getAddress(), employeeInforDTO.getIdCard(), false, employeeInforDTO.getAvatar()));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataException(InvalidDataException ex) {
        List<ValidationError> errors1 = ex.getErrors();
        Map<String, String> errors = new HashMap<>();
        errors1.forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
