package com.example.spring_pawn_app.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTODetail {
    private String customerCode;
    private String name;
    private Integer gender;
    private LocalDate dateOfBirth;
    private String identityCard;
    private String phoneNumber;
    private String email;
    private String address;
    private String avatar;
    private String note;
    private Long contractQuantity;
}