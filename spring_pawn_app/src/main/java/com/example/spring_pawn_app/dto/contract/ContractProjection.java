package com.example.spring_pawn_app.dto.contract;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.model.Status;

import java.time.LocalDate;

public interface ContractProjection{
    Integer getId();
    String getContract_code();
    LocalDate getBegin_date();
    LocalDate getEnd_date();
    Customer getCustomer();
    interface Customer{
        Integer getId();
        String getCustomer_name();
    }

    Status getStatus();
    interface Status{
        Integer getId();
        String getName();
    }
    Double getInterest();
    Employee getEmployee();
    interface Employee{
        Integer getId();
        String getName();
    }
    Product getProduct();
    interface Product{
        Integer getId();
        String getName();
    }

}
