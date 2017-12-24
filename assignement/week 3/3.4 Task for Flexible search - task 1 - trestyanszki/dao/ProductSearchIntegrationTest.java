/**
 *
 */
package org.training.dao;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Integration test for product search.
 *
 * @author Peter Trestyanszki
 */
@IntegrationTest
public class ProductSearchIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private ProductSearchDao productSearchDAO;

	@Resource
	private ModelService modelService;

	private CatalogVersionModel catalogVersion;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		setCatalogVersionDefaultStaged();
		saveTestProductToDB();
	}

	@Test
	public void testSearchExistingProductInCatalog()
	{
		final String productCode = "1234567";
		final ProductModel product = productSearchDAO.searchByCodeInCatalog(productCode, catalogVersion);
		Assert.assertNotNull(product);
		Assert.assertTrue(productCode.equals(product.getCode()));
	}

	@Test
	public void testSearchNonExistingProductInCatalog()
	{
		final String productCode = "12345678";
		try
		{
			productSearchDAO.searchByCodeInCatalog(productCode, catalogVersion);
		}
		catch (AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			Assert.assertTrue(e instanceof UnknownIdentifierException);
		}
	}

	@Test
	public void testMissingArgument()
	{
		final String productCode = null;
		try
		{
			productSearchDAO.searchByCodeInCatalog(productCode, catalogVersion);
		}
		catch (AmbiguousIdentifierException | UnknownIdentifierException | IllegalArgumentException e)
		{
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	private void saveTestProductToDB()
	{

		final ProductModel product = modelService.create(ProductModel.class);
		product.setCode("1234567");
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);
		product.setCatalogVersion(catalogVersion);
		product.setName("Test Product");
		try
		{
			modelService.save(product);
		}

		catch (final ModelSavingException e)
		{
			Assert.fail(e.getMessage());
		}

	}

	private void setCatalogVersionDefaultStaged()
	{
		catalogVersion = catalogVersionService.getCatalogVersion("Default", "Staged");
	}
}
