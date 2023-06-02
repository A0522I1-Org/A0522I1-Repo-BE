package com.example.spring_pawn_app.service.employee;

import com.example.spring_pawn_app.dto.custom_error.InvalidDataException;
import com.example.spring_pawn_app.dto.custom_error.ValidationError;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.repository.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository iEmployeeRepository;


    @Override
    public Employee findEmployeeByUserName(String username) {
        return iEmployeeRepository.findEmployeeByUserName(username);
    }

    @Override
    public Employee finById(Integer id) {
        return iEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        List<ValidationError> errors = new ArrayList<>();
        Employee employee1 = this.finById(employee.getId());//employee current
        List<Employee> list1 = this.findByEmails(employee.getEmail());
        List<Employee> list2 = this.findByPhones(employee.getPhone());
        List<Employee> list3 = this.findByIdCards(employee.getIdCard());
        if (list1.size() > 0 && !employee1.getEmail().equals(employee.getEmail())) {
            errors.add(new ValidationError("duplicateEmail", "Email đã được đăng kí."));
        }
        if (list2.size() > 0 && !employee1.getPhone().equals(employee.getPhone())) {
            errors.add(new ValidationError("duplicatePhone", "Số điện thoại đã được đăng kí."));
        }
        if (list3.size() > 0 && !employee1.getIdCard().equals(employee.getIdCard())) {
            errors.add(new ValidationError("duplicateIdCard", "Hộ chiếu/CMND đã được đăng kí."));
        }
        if (!errors.isEmpty()) {
            throw new InvalidDataException(errors);
        }
        if (errors.isEmpty()){

            iEmployeeRepository.save(employee);
        }
    }

    @Override

    public List<Employee> findByEmails(String email) {
        return iEmployeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> findByPhones(String phone) {
        return iEmployeeRepository.findByPhone(phone);
    }
    @Override
    public List<Employee> findByIdCards(String idCard) {
        return iEmployeeRepository.findByIdCard(idCard);
    }

    @Override
    public Employee findByEmail(String email) {
        return iEmployeeRepository.findEmployeeByEmail(email);
    }

}
