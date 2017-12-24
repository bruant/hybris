/**
 *
 */
package org.training.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.training.dao.ProductSearchDao;


/**
 * @author Peter Trestyanszki
 * @see ProductSearchService
 */
@Component(value = "productSearchService")
public class ProductSearchServiceImpl implements ProductSearchService
{

	@Resource
	private ProductSearchDao productSearchDAO;

	@Override
	public ProductModel searchByCodeInCatalog(final String productCode, final CatalogVersionModel catalogeVersion)
			throws IllegalArgumentException, AmbiguousIdentifierException, UnknownIdentifierException
	{
		return productSearchDAO.searchByCodeInCatalog(productCode, catalogeVersion);
	}


}
