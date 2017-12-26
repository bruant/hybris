/**
 *
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.impl.SearchResultImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * JUnit test for image description.
 *
 * @author Peter Trestyanszki
 *
 */
public class ImageDescriptionJUnitTest
{

	@InjectMocks
	private PrimaryImageDaoImpl dao;

	@Mock
	private FlexibleSearchService flexibleSearchService;


	@Before
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDescriptionWhenProductHasDescription()
	{
		Mockito.when(flexibleSearchService.search((FlexibleSearchQuery) Mockito.anyObject())).thenReturn(getTestMediaResult());
		final String description = dao.getImageDescription("1234567", new CatalogVersionModel());
		Assert.assertTrue("Test Description".equals(description));
	}

	@Test
	public void testGetDescriptionWhenProductDoesntHaveDescription()
	{
		Mockito.when(flexibleSearchService.search((FlexibleSearchQuery) Mockito.anyObject()))
				.thenReturn(getTestMediResultWithoutDescription());
		final String description = dao.getImageDescription("1234567", new CatalogVersionModel());
		Assert.assertTrue(description == null);
	}

	@Test
	public void testGetDescriptionWhenMissingProductCode()
	{
		try
		{
			dao.getImageDescription(null, new CatalogVersionModel());
		}
		catch (final Exception e)
		{
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testGetDescriptionWhenMissingCatalogVersion()
	{
		try
		{
			dao.getImageDescription("1234567", null);
		}
		catch (final Exception e)
		{
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	private SearchResult<Object> getTestMediaResult()
	{
		final List<MediaModel> list = new ArrayList<>();
		final MediaModel model = new MediaModel();
		model.setDataPK(Long.valueOf(12234));
		model.setDescription("Test Description");
		list.add(model);
		final SearchResult result = new SearchResultImpl<MediaModel>(list, 1, 1, 0);
		return result;
	}

	private SearchResult<Object> getTestMediResultWithoutDescription()
	{
		final List<MediaModel> list = new ArrayList<>();
		final MediaModel model = new MediaModel();
		model.setDataPK(Long.valueOf(12234));
		list.add(model);
		final SearchResult result = new SearchResultImpl<MediaModel>(list, 1, 1, 0);
		return result;
	}

	private CatalogVersionModel createCatalogVersionModel()
	{
		final CatalogVersionModel model = Mockito.mock(CatalogVersionModel.class);
		Mockito.when(model.getPk()).thenReturn((PK) Mockito.anyObject());
		return model;
	}

}
