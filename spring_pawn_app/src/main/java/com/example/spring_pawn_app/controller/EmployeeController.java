package com.example.spring_pawn_app.controller;


import com.example.spring_pawn_app.dto.EmployeeInforDTO;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.service.employee.EmployeeService;
import com.example.spring_pawn_app.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeInforDTO> findByIdEmployee(@PathVariable Integer id) {
        EmployeeInforDTO employeeInforDTO = new EmployeeInforDTO();
        Employee employee = employeeService.finById(id);
        User user = userService.findByIdEmployee(id);
        BeanUtils.copyProperties(employee, employeeInforDTO);
        employeeInforDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeInforDTO.setUserName(user.getUserName());
        employeeInforDTO.setPassword(user.getPassword());
        employeeInforDTO.setConfirmPassword(user.getPassword());
        Optional<EmployeeInforDTO> dto = Optional.of(employeeInforDTO);
        return ResponseEntity.of(dto);
    }

    @PutMapping(value = "/employee/save")
    public ResponseEntity<?> updateEmployeeInfor(@Valid @RequestBody EmployeeInforDTO employeeInforDTO,BindingResult bindingResult) {
        new EmployeeInforDTO().validate(employeeInforDTO,bindingResult);
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> {
                        String fieldName =  error.getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    });
            return  ResponseEntity.badRequest().body(errors);
        }
        User user = userService.findByIdEmployee(employeeInforDTO.getId());
        user = new User(user.getId(), employeeInforDTO.getUserName(), employeeInforDTO.getPassword(), user.getEmployee(), user.isFlag());
        userService.save(user);
        employeeService.save(new Employee(employeeInforDTO.getId(), employeeInforDTO.getName(), employeeInforDTO.getDateOfBirth(), employeeInforDTO.getPhone(), employeeInforDTO.getEmail(), employeeInforDTO.getGender(), employeeInforDTO.getAddress(), employeeInforDTO.getIdCard(), false, employeeInforDTO.getAvatar()));
        return  ResponseEntity.ok("Cập nhập thông tin nhân viên thành công");
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
