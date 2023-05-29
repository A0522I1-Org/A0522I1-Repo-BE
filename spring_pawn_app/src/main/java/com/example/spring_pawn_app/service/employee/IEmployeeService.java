package com.example.spring_pawn_app.service.employee;

import com.example.spring_pawn_app.model.Employee;

public interface IEmployeeService {
    Employee findEmployeeByUserName(String username);
    Employee findByEmail(String email);
}
