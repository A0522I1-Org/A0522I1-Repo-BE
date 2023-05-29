package com.example.spring_pawn_app.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @author Trần Thế Huy
 * @version 1
 * @since 28/5/2023
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTOList {

    private Integer id;

    private String customerCode;

    private String name;

    private Integer gender;

    private LocalDate dateOfBirth;

    private String phone;

    private String identityCard;

    private Long contractQuantity;
}
