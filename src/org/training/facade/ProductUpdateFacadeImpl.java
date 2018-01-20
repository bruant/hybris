/**
 *
 */
package org.training.facade;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.training.service.ProductUpdateService;


/**
 *
 */
@Component(value = "productUpdateFacade")
public class ProductUpdateFacadeImpl implements ProductUpdateFacade
{

	@Resource
	private ProductUpdateService productUpdateService;

	@Resource
	private CatalogVersionService catalogVersionService;


	@Override
	public String updateManufacturerNameForProducts(final String manufacturerName, final List<String> productCodes)
	{
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("Default", "Staged");
		catalogVersionService.addSessionCatalogVersion(catalogVersion);

		return productUpdateService.updateManufacturerNameForProducts(manufacturerName, productCodes);

	}

}
