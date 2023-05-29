package com.example.spring_pawn_app.service.employee;

import com.example.spring_pawn_app.model.Employee;

import java.util.List;


public interface IEmployeeService {
    Employee finById(Integer id);

    void save(Employee employee);

    List<Employee> findByEmails(String email);

    List<Employee> findByPhones(String phone);

    List<Employee> findByIdCards(String idCard);
}
