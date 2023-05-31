package com.example.spring_pawn_app.service.employee;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.repository.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    IEmployeeRepository iEmployeeRepository;
    @Override
    public Employee findEmployeeByUserName(String username) {
        return iEmployeeRepository.findEmployeeByUserName(username);
    }

    @Override
    public Employee findByEmail(String email) {
        return iEmployeeRepository.findEmployeeByEmail(email);
    }

}
