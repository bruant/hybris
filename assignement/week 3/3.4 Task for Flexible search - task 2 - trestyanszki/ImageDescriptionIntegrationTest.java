/**
 *
 */
package org.training.dao;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Integration test for image description.
 *
 * @author Peter Trestyanszki
 */
@IntegrationTest
public class ImageDescriptionIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private PrimaryImageDao primaryImageDAO;

	@Resource
	private ModelService modelService;

	private CatalogVersionModel catalogVersion;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		setCatalogVersionDefaultStaged();
	}

	@Test
	public void testGetDescriptionWhenProductHasDescription()
	{
		final String productCode = "11111";
		final String productName = "Hook";
		final String mediaCode = "11112";
		final String mediaDescription = "Test Description";

		saveTestProductToDB(productCode, productName, mediaCode, mediaDescription);

		final String description = primaryImageDAO.getImageDescription(productCode, catalogVersion);
		Assert.assertTrue(mediaDescription.equals(description));
	}

	@Test
	public void testGetDescriptionWhenProductDoesntHaveImage()
	{
		final String productCode = "11112";
		final String productName = "Hook";
		final String mediaCode = null;
		final String mediaDescription = null;

		saveTestProductToDB(productCode, productName, mediaCode, mediaDescription);

		final String description = primaryImageDAO.getImageDescription(productCode, catalogVersion);
		Assert.assertTrue(description == null);
	}

	@Test
	public void testGetDescriptionWhenProductDoesntHaveDescription()
	{
		final String productCode = "11113";
		final String productName = "Hook";
		final String mediaCode = "22223";
		final String mediaDescription = null;

		saveTestProductToDB(productCode, productName, mediaCode, mediaDescription);
		final String description = primaryImageDAO.getImageDescription(productCode, catalogVersion);
		Assert.assertTrue(description == null);
	}

	@Test
	public void testMissingArgument()
	{
		final String productCode = null;
		try
		{
			primaryImageDAO.getImageDescription(productCode, catalogVersion);
		}
		catch (final Exception e)
		{
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	private void saveTestProductToDB(final String productCode, final String productName, final String mediaCode,
			final String mediaDescription)
	{

		final ProductModel product = createProduct(productCode, productName);
		if (mediaCode != null)
		{
			product.setPicture(createMedia(mediaCode, mediaDescription));
		}

		try
		{
			modelService.save(product);
		}

		catch (final ModelSavingException e)
		{
			Assert.fail(e.getMessage());
		}

	}

	private ProductModel createProduct(final String productCode, final String productName)
	{
		final ProductModel product = modelService.create(ProductModel.class);
		product.setCode(productCode);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);
		product.setCatalogVersion(catalogVersion);
		product.setName(productName);
		return product;
	}

	private MediaModel createMedia(final String code, final String description)
	{

		final MediaModel model = modelService.create(MediaModel.class);
		model.setCode(code);
		model.setCatalogVersion(catalogVersion);
		model.setDescription(description);

		return model;
	}


	private void setCatalogVersionDefaultStaged()
	{
		catalogVersion = catalogVersionService.getCatalogVersion("Default", "Staged");
	}
}
