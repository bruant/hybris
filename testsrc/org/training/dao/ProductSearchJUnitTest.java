/**
 *
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

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
 * JUnit test for product search.
 *
 * @author Peter Trestyanszki
 *
 */
public class ProductSearchJUnitTest
{

	@InjectMocks
	private ProductSearchDaoImpl dao;

	@Mock
	private FlexibleSearchService flexibleSearchService;


	@Before
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSearchForProductInCatalog()
	{
		Mockito.when(flexibleSearchService.getModelsByExample((ProductModel) Mockito.anyObject())).thenReturn(getTestProductList());
		final ProductModel product = dao.searchByCodeInCatalog("1234567", new CatalogVersionModel());
		Assert.assertTrue("1234567".equals(product.getCode()));
	}

	@Test
	public void testSearchForProductInCatalogNoResult()
	{
		Mockito.when(flexibleSearchService.getModelsByExample((ProductModel) Mockito.anyObject()))
				.thenReturn(getTestEmptyProductList());
		try
		{
			dao.searchByCodeInCatalog("1234567", new CatalogVersionModel());
		}
		catch (AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			Assert.assertTrue(e instanceof UnknownIdentifierException);
		}
	}

	@Test
	public void testSearchForProductInCatalogMoreCount()
	{
		Mockito.when(flexibleSearchService.getModelsByExample((ProductModel) Mockito.anyObject()))
				.thenReturn(getTestProductListWithMoreCount());
		try
		{
			dao.searchByCodeInCatalog("1234567", new CatalogVersionModel());
		}
		catch (AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			Assert.assertTrue(e instanceof AmbiguousIdentifierException);
		}
	}

	@Test
	public void testSearchForProductInCatalogMissingCode()
	{
		try
		{
			dao.searchByCodeInCatalog(null, new CatalogVersionModel());
		}
		catch (AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testSearchForProductInCatalogMissingCatalog()
	{
		try
		{
			dao.searchByCodeInCatalog("1234567", null);
		}
		catch (AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	private List<ProductModel> getTestProductList()
	{
		final List<ProductModel> list = new ArrayList<>();
		final ProductModel model = new ProductModel();
		model.setCode("1234567");
		list.add(model);
		return list;
	}

	private List<ProductModel> getTestEmptyProductList()
	{
		return new ArrayList<ProductModel>();
	}

	private List<ProductModel> getTestProductListWithMoreCount()
	{
		final List<ProductModel> list = new ArrayList<>();
		final ProductModel model = new ProductModel();
		model.setCode("1234567");
		list.add(model);
		list.add(model);
		return list;
	}

}
