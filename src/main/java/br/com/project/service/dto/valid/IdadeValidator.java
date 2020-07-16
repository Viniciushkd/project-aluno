package br.com.project.service.dto.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdadeValidator implements ConstraintValidator<IdadeConstraint, Integer> {

	@Override
	public void initialize(IdadeConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == 0) {
			return false;
		}
		return true;
	}
}
