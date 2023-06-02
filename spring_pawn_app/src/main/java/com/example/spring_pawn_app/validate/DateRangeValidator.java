package com.example.spring_pawn_app.validate;

import com.example.spring_pawn_app.dto.ContractCreateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Create by ThuongVTH
 * Date create: 02/06/2023
 */
public class DateRangeValidator implements ConstraintValidator<DateRange, ContractCreateDto> {
    @Override
    public void initialize(DateRange constraintAnnotation) {
        // Khởi tạo validator
    }

    @Override
    public boolean isValid(ContractCreateDto contract, ConstraintValidatorContext context) {
        if (contract.getBeginDate() == null || contract.getEndDate() == null) {
            // Nếu beginDate hoặc endDate là null, không thực hiện kiểm tra
            return true;
        }

        return !contract.getBeginDate().isAfter(contract.getEndDate());
    }
}
