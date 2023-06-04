package com.example.spring_pawn_app.mapper;


import com.example.spring_pawn_app.dto.customer.CustomerDTODetail;
import com.example.spring_pawn_app.dto.customer.CustomerDTOList;
import com.example.spring_pawn_app.dto.customer.CustomerDTORestore;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Trần Thế Huy
 * @version 1
 * @since 28/5/2023
 */
@Component
public class CustomerMapper {


    private final ModelMapper modelMapper;

    public CustomerMapper() {
        this.modelMapper = new ModelMapper();
    }

    public CustomerDTOList mapToDtoList(Object[] row) {
        return CustomerDTOList.builder()
                .id((Integer) row[0])
                .customerCode((String) row[1])
                .name((String) row[2])
                .gender((Integer) row[3])
                .dateOfBirth((LocalDate) row[4])
                .phone((String) row[5])
                .identityCard((String) row[6])
                .contractQuantity((Long) row[7])
                .build();
    }

    public CustomerDTODetail mapToDtoDetail(Object[] row) {
        return CustomerDTODetail.builder()
                .id((Integer) row[0])
                .customerCode((String) row[1])
                .name((String) row[2])
                .gender((Integer) row[3])
                .dateOfBirth((LocalDate) row[4])
                .identityCard((String) row[5])
                .phone((String) row[6])
                .email((String) row[7])
                .address((String) row[8])
                .avatar((String) row[9])
                .note((String) row[10])
                .contractQuantity((Long) row[11])
                .build();
    }

    public CustomerDTORestore mapToDtoRestore(Object[] row) {
        return CustomerDTORestore.builder()
                .id((Integer) row[0])
                .customerCode((String) row[1])
                .name((String) row[2])
                .phone((String) row[3])
                .identityCard((String) row[4])
                .deleteTime((LocalDateTime) row[5])
                .build();
    }
}
