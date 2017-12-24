/**
 *
 */
package org.training.facade;

import org.training.dto.ProductDTO;


/**
 * Facade interface for product search.
 *
 * @author Peter Trestyanszki
 *
 */
public interface ProductSearchFacade
{
	ProductDTO searchByCodeInCatalog(String productCode, String catalog, String version);

}
