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
public class CustomerDTO {
    private Integer id;
    private String customerCode;
    private String name;
    private LocalDate dateOfBirth;
    private Integer gender;
    private String email;
    private String address;
    private String phone;
    private String identityCard;
    private String avatar;
}
