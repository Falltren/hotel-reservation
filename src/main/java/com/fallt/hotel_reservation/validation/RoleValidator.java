package com.fallt.hotel_reservation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class RoleValidator implements ConstraintValidator<RoleValidation, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(RoleValidation annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
