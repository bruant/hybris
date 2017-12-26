/**
 *
 */
package org.training.dto;

/**
 * @author Peter Trestyanszki
 *
 */
public class ProductDTO
{

	private String code, catalog, version, error, primaryImageDescription;

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
	 * @return the error
	 */
	public String getError()
	{
		return error;
	}

	/**
	 * @param error
	 *           the error to set
	 */
	public void setError(final String error)
	{
		this.error = error;
	}

	/**
	 * @return the primaryImageDescription
	 */
	public String getPrimaryImageDescription()
	{
		return primaryImageDescription;
	}

	/**
	 * @param primaryImageDescription
	 *           the primaryImageDescription to set
	 */
	public void setPrimaryImageDescription(final String primaryImageDescription)
	{
		this.primaryImageDescription = primaryImageDescription;
	}
}
