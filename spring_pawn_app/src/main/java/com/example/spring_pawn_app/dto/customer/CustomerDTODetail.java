package com.example.spring_pawn_app.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @author Trần Thế Huy
 * @version 2
 * @since 6/6/2023
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTODetail {

    private Integer id;

    private String customerCode;

    private String name;

    private Integer gender;

    private LocalDate dateOfBirth;

    private String identityCard;

    private String phone;

    private String email;

    private String address;

    private String avatar;

    private String note;

    private Long contractQuantity;
}
