/**
 *
 */
package org.training.facade;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.training.dto.ProductDTO;
import org.training.service.ProductSearchService;


/**
 * @author Peter Trestyanszki
 * @see ProductSearchFacade
 *
 */
@Component(value = "flexibleSearchFacade")
public class ProductSearchFacadeImpl implements ProductSearchFacade
{
	private static final Logger LOG = Logger.getLogger(ProductSearchFacadeImpl.class);

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private ProductSearchService productSearchService;


	@Override
	public ProductDTO searchByCodeInCatalog(final String productCode, final String catalog, final String version)
	{

		final ProductDTO dto = new ProductDTO();

		final CatalogVersionModel catalogVersion = setCatalogVersion(catalog, version, dto);

		if (catalogVersion != null)
		{
			searchProduct(productCode, dto, catalogVersion);
		}

		return dto;
	}

	private void searchProduct(final String productCode, final ProductDTO dto, final CatalogVersionModel catalogVersion)
	{
		try
		{
			final ProductModel productModel = productSearchService.searchByCodeInCatalog(productCode, catalogVersion);
			convertModelToDTO(dto, productModel);
		}
		catch (final AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			LOG.error(e.getMessage());
			dto.setError(e.getMessage());
		}
	}

	private CatalogVersionModel setCatalogVersion(final String catalog, final String version, final ProductDTO dto)
	{
		CatalogVersionModel catalogVersion = null;
		try
		{
			catalogVersion = catalogVersionService.getCatalogVersion(catalog, version);
			catalogVersionService.addSessionCatalogVersion(catalogVersion);
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.error(e.getMessage());
			dto.setError(e.getMessage());
		}
		return catalogVersion;
	}

	private ProductDTO convertModelToDTO(final ProductDTO dto, final ProductModel productModel)
	{
		dto.setCode(productModel.getCode());
		dto.setCatalog(productModel.getCatalogVersion().getCatalog().getId());
		dto.setVersion(productModel.getCatalogVersion().getVersion());
		return dto;
	}

}
