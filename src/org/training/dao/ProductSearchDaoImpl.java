/**
 *
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


/**
 * @author Peter Trestyanszki
 * @see ProductSearchDao
 */
@Component(value = "productSearchDAO")
public class ProductSearchDaoImpl implements ProductSearchDao
{

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public ProductModel searchByCodeInCatalog(final String productCode, final CatalogVersionModel catalogVersion)
			throws IllegalArgumentException, AmbiguousIdentifierException, UnknownIdentifierException
	{
		validateArguments(productCode, catalogVersion);
		final ProductModel exampleModel = setQueryParameters(productCode, catalogVersion);
		final List<ProductModel> products = flexibleSearchService.getModelsByExample(exampleModel);
		validateResultSet(productCode, products);
		return products.get(0);
	}

	private ProductModel setQueryParameters(final String productCode, final CatalogVersionModel catalogVersion)
	{
		final ProductModel exampleModel = new ProductModel();
		exampleModel.setCode(productCode);
		exampleModel.setCatalogVersion(catalogVersion);
		return exampleModel;
	}

	private void validateArguments(final String productCode, final CatalogVersionModel catalog) throws IllegalArgumentException
	{
		if (productCode == null)
		{
			throw new IllegalArgumentException("Product code is manatory for product search!");
		}
		if (catalog == null)
		{
			throw new IllegalArgumentException("Catalog is manatory for product search!");
		}
	}

	private void validateResultSet(final String productCode, final List<ProductModel> products)
			throws UnknownIdentifierException, AmbiguousIdentifierException
	{
		if (products.isEmpty())
		{
			throw new UnknownIdentifierException("Product could not be found with code of " + productCode + "!");
		}
		else if (products.size() != 1)
		{
			throw new AmbiguousIdentifierException("More than one products could be found with code of " + productCode + "!");
		}
	}

}
