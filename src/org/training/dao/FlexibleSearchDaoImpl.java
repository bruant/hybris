/**
 *
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;


/**
 * @author Hp
 *
 */
public class FlexibleSearchDaoImpl implements FlexibleSearchDao
{

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<ProductModel> getProductsByProductCodeAndCatalogVersion(final String productCode,
			final CatalogVersionModel catalogVersion)
	{
		final ProductModel exampleModel = new ProductModel();
		exampleModel.setCode(productCode);
		if (catalogVersion != null)
		{
			exampleModel.setCatalogVersion(catalogVersion);
		}

		return flexibleSearchService.getModelsByExample(exampleModel);
	}

}
