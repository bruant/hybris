/**
 *
 */
package org.training.model;

import org.training.dto.ProductDTO;


/**
 * @author Hp
 *
 */
public class ProductSearchModel
{

	private String code, catalog, version;
	private ProductDTO product;

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

	/**
	 * @return the catalog
	 */
	public String getCatalog()
	{
		return catalog;
	}

	/**
	 * @param catalog
	 *           the catalog to set
	 */
	public void setCatalog(final String catalog)
	{
		this.catalog = catalog;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String to = "Input: " + code + " - " + catalog + "\n" + "Product:";
		if (product != null)
		{
			to += product.getCode();
		}
		return to;
	}

	/**
	 * @return the version
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * @param version
	 *           the version to set
	 */
	public void setVersion(final String version)
	{
		this.version = version;
	}

	/**
	 * @return the product
	 */
	public ProductDTO getProduct()
	{
		return product;
	}

	/**
	 * @param product
	 *           the product to set
	 */
	public void setProduct(final ProductDTO product)
	{
		this.product = product;
	}
}
