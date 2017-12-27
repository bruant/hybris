/**
 *
 */
package org.training.interceptor;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Peter Trestyanszki
 *
 */
public class DoBInterceptor implements ValidateInterceptor
{

	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof UserModel)
		{
			final UserModel user = (UserModel) model;
			final Date dob = user.getDateOfBirth();

			final Calendar dobCal = Calendar.getInstance();
			dobCal.setTime(dob);
			final LocalDate birthday = LocalDate.of(dobCal.get(Calendar.YEAR), dobCal.get(Calendar.MONTH) + 1,
					dobCal.get(Calendar.DAY_OF_MONTH));
			final long years = birthday.until(LocalDate.now(), ChronoUnit.YEARS);
			if (years < 12)
			{
				throw new InterceptorException("Age is under 12!");
			}
		}

	}
}
