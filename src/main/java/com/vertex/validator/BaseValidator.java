package com.vertex.validator;


import com.vertex.exceptions.ErrorKey;
import com.vertex.exceptions.InvalidInputParameterException;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class BaseValidator {

    void validateFieldNotEmpty(final String field, final ErrorKey errorKey) {
        if (!hasText(field.trim())) {
            throw new InvalidInputParameterException(errorKey);
        }
    }

    void validateFieldNotNull(final Object field, final ErrorKey errorKey) {
        if (isNull(field)) {
            throw new InvalidInputParameterException(errorKey);
        }
    }

    void validateFieldNotNullNorEmpty(String field, ErrorKey errorKey) {
        validateFieldNotNull(field, errorKey);
        validateFieldNotEmpty(field, errorKey);
    }
}
