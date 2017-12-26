package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;


/**
 * DAO interface to get primary image description.
 *
 * @author Peter Trestyanszki
 */
public interface PrimaryImageDao
{

	/**
	 * Search for products by code in a given catalog.
	 *
	 * @param productCode
	 *           Product identifier.
	 * @param catalogVersion
	 *           Represents catalog (name and version).
	 * @return Image description.
	 *
	 * @throws java.lang.IllegalArgumentException
	 *            in case of product code or catalog version is missing
	 */
	String getImageDescription(String productCode, CatalogVersionModel catalogVersion) throws IllegalArgumentException;
}
