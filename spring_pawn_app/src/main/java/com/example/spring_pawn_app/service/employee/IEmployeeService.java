package com.example.spring_pawn_app.service.employee;

import com.example.spring_pawn_app.model.Employee;


public interface IEmployeeService {
    Employee finById(Integer id);
    void save(Employee employee);
}
