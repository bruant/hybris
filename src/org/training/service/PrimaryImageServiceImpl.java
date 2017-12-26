/**
 *
 */
package org.training.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.training.dao.PrimaryImageDao;


/**
 * @author Peter Trestyanszki
 * @see PrimaryImageService
 */
@Component(value = "primaryImageService")
public class PrimaryImageServiceImpl implements PrimaryImageService
{

	@Resource
	private PrimaryImageDao primaryImageDAO;

	@Override
	public String getImageDescription(final String productCode, final CatalogVersionModel catalogeVersion)
			throws IllegalArgumentException
	{
		return primaryImageDAO.getImageDescription(productCode, catalogeVersion);
	}


}
