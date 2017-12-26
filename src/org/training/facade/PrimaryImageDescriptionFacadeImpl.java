/**
 *
 */
package org.training.facade;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.training.dto.ProductDTO;
import org.training.service.PrimaryImageService;


/**
 * @author Peter Trestyanszki
 * @see PrimaryImageDescriptionFacade
 *
 */
@Component(value = "primaryImageDescriptionFacade")
public class PrimaryImageDescriptionFacadeImpl implements PrimaryImageDescriptionFacade
{
	private static final Logger LOG = Logger.getLogger(PrimaryImageDescriptionFacadeImpl.class);

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private PrimaryImageService primaryImageService;


	@Override
	public ProductDTO getPrimaryImageDescription(final String productCode, final String catalog, final String version)
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
			final String description = primaryImageService.getImageDescription(productCode, catalogVersion);
			dto.setPrimaryImageDescription(description);
		}
		catch (final IllegalArgumentException e)
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

}
