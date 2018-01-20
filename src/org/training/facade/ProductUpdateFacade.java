/**
 *
 */
package org.training.facade;

import java.util.List;


/**
 *
 */
public interface ProductUpdateFacade
{
	String updateManufacturerNameForProducts(String manufacturerName, List<String> productCodes);
}
