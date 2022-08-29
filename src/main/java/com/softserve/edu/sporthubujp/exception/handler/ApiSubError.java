package com.softserve.edu.sporthubujp.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

interface ApiSubError {

}

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationError implements ApiSubError {
    private List<Object> object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(List<Object> object, String message) {
        this.object = object;
        this.message = message;
    }
}
