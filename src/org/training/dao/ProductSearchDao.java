package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;


/**
 * DAO interface to search for products.
 *
 * @author Peter Trestyanszki
 */
public interface ProductSearchDao
{

	/**
	 * Search for products by code in a given catalog.
	 *
	 * @param productCode
	 *           Product identifier.
	 * @param catalogVersion
	 *           Represents catalog (name and version).
	 * @return Product assigned to the product code.
	 *
	 * @throws java.lang.IllegalArgumentException
	 *            in case of product code or catalog version is missing
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more then one product found for a given code
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no product found for given code
	 */
	ProductModel searchByCodeInCatalog(String productCode, CatalogVersionModel catalogVersion)
			throws IllegalArgumentException, AmbiguousIdentifierException, UnknownIdentifierException;
}
