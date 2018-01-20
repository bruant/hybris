/**
 *
 */
package org.training.service;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.tx.Transaction;

import java.util.List;

import org.apache.log4j.Logger;


/**
 *
 */
public class ProductUpdateServiceImpl extends DefaultProductService implements ProductUpdateService
{

	private static final Logger LOG = Logger.getLogger(ProductUpdateService.class);


	@Override
	public String updateManufacturerNameForProducts(final String manufacturerName, final List<String> productCodes)
	{
		String retVal = "Update is done.";

		final Transaction tx = Transaction.current();
		tx.begin();

		try
		{
			for (final String code : productCodes)
			{
				final ProductModel product = getProductForCode(code);
				product.setManufacturerName(manufacturerName);
				getModelService().save(product);
			}

			tx.commit();
		}
		catch (final ModelSavingException e)
		{
			LOG.error(e);
			retVal = "Update rolled back started.";
			tx.rollback();
			retVal = "Update is rolled back.";
		}

		return retVal;
	}

}
