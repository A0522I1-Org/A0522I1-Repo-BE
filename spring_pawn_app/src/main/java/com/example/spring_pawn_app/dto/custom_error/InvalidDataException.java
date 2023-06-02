package com.example.spring_pawn_app.dto.custom_error;

import java.util.List;

public class InvalidDataException extends RuntimeException{
    private List<ValidationError> errors;

    public InvalidDataException(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
