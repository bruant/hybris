/**
 *
 */
package org.training.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 *
 */
public class NotUnderAgeValidator implements ConstraintValidator<NotUnderAge, Integer>
{

	@Override
	public void initialize(final NotUnderAge constraintAnnotation)
	{
		// empty
	}

	@Override
	public boolean isValid(final Integer value, final ConstraintValidatorContext context)
	{
		return value != null && value.intValue() >= 18;
	}
}
