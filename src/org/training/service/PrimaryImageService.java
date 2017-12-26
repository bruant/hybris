/**
 *
 */
package org.training.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;


/**
 * Service interface to search for products.
 *
 * @author Peter Trestyanszki
 *
 */
public interface PrimaryImageService
{
	/**
	 * Service to get image description.
	 *
	 * @param productCode
	 *           Product identifier.
	 * @param catalogVersion
	 *           Represents catalog (name and version).
	 * @return Product as a result of the search.
	 *
	 * @throws java.lang.IllegalArgumentException
	 *            if any of the arguments is null
	 */
	String getImageDescription(String productCode, CatalogVersionModel catalogVersion) throws IllegalArgumentException;
}
