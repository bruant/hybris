/**
 *
 */
package org.training.service;

import de.hybris.platform.product.ProductService;

import java.util.List;


/**
 *
 */
public interface ProductUpdateService extends ProductService
{
	String updateManufacturerNameForProducts(String manufacturerName, List<String> productCodes);
}
