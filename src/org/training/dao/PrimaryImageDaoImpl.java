/**
 *
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


/**
 * @author Peter Trestyanszki
 * @see PrimaryImageDao
 */
@Component(value = "primaryImageDAO")
public class PrimaryImageDaoImpl implements PrimaryImageDao
{

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public String getImageDescription(final String productCode, final CatalogVersionModel catalogVersion)
			throws IllegalArgumentException
	{
		validateArguments(productCode, catalogVersion);
		final String query = "SELECT {m.pk}, {m.description} FROM {Media as m JOIN Product as p ON {m.pk} = {p.picture}} "
				+ "WHERE {p.code} = ?code and {p.catalogversion} = ?catalogversion";

		final Map<String, String> parameters = new HashMap<String, String>(2);
		parameters.put("code", productCode);
		parameters.put("catalogversion", "" + catalogVersion.getPk());
		final FlexibleSearchQuery flexiQuery = new FlexibleSearchQuery(query, parameters);
		final SearchResult<MediaModel> medias = flexibleSearchService.search(flexiQuery);
		return medias.getResult().isEmpty() ? null : medias.getResult().get(0).getDescription();
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

}
