/**
 *
 */
package org.training.facade;

import org.training.dto.ProductDTO;


/**
 * Facade interface for primary image description.
 *
 * @author Peter Trestyanszki
 *
 */
public interface PrimaryImageDescriptionFacade
{
	ProductDTO getPrimaryImageDescription(String productCode, String catalog, String version);

}
