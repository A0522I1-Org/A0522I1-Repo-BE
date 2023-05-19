package com.example.spring_pawn_app.service.employee;

import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.repository.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository iEmployeeRepository;
    @Override
    public Employee finById(Integer id) {
        return iEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }
}
