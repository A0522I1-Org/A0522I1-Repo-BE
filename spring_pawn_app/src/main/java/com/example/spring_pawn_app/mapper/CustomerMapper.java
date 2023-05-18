package com.example.spring_pawn_app.mapper;

import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomerMapper {
    public CustomerDTODetail mapToDTODetail(Object[] row) {
        return CustomerDTODetail.builder()
                .customerCode((String) row[0])
                .name((String) row[1])
                .gender((Integer) row[2])
                .dateOfBirth((LocalDate) row[3])
                .identityCard((String) row[4])
                .phoneNumber((String) row[5])
                .email((String) row[6])
                .address((String) row[7])
                .avatar((String) row[8])
                .note((String) row[9])
                .contractQuantity((Long) row[10])
                .build();
    }
}
