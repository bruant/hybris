/**
 *
 */
package org.training.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;


/**
 *
 */
public class ProductUpdateModel
{

	private String manufacturer, products, result;

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer()
	{
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *           the manufacturer to set
	 */
	public void setManufacturer(final String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the products
	 */
	public String getProducts()
	{
		return products;
	}

	/**
	 * @param products
	 *           the products to set
	 */
	public void setProducts(final String products)
	{
		this.products = products;
	}

	/**
	 * @return the result
	 */
	public String getResult()
	{
		return result;
	}

	/**
	 * @param result
	 *           the result to set
	 */
	public void setResult(final String result)
	{
		this.result = result;
	}


	public List<String> getProductsAsList()
	{
		if (StringUtils.isEmpty(products))
		{
			return null;
		}
		else
		{
			return Arrays.asList(products.split(","));
		}
	}

	@Override
	public String toString()
	{
		return "Manufacturer: " + manufacturer + "\n Products: " + products;
	}

}
