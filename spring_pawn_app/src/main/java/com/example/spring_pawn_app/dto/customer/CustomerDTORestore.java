package com.example.spring_pawn_app.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Trần Thế Huy
 * @version 1
 * @since 28/5/2023
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTORestore {
    private Integer id;

    private String customerCode;

    private String name;

    private String identityCard;

    private String phone;

    private LocalDateTime deleteTime;
}
