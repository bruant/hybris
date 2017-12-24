/**
 *
 */
package org.training.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;


/**
 * Service interface to search for products.
 *
 * @author Peter Trestyanszki
 *
 */
public interface ProductSearchService
{
	/**
	 * Search for products by code in a given catalog.
	 *
	 * @param productCode
	 *           Product identifier.
	 * @param catalogVersion
	 *           Represents catalog (name and version).
	 * @return Product as a result of the search.
	 *
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more then one product found for a given code
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no product found for given code
	 */
	ProductModel searchByCodeInCatalog(String productCode, CatalogVersionModel catalogVersion)
			throws IllegalArgumentException, AmbiguousIdentifierException, UnknownIdentifierException;
}
