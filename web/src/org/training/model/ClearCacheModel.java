/**
 *
 */
package org.training.model;

import java.util.List;


/**
 * Peter Trestyanszki
 */
public class ClearCacheModel
{
	private String selectedRegion;

	private String region;

	private String item;

	private String media;

	private String result;

	private List<String> regions;

	/**
	 * @return the region
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * @param region
	 *           the region to set
	 */
	public void setRegion(final String region)
	{
		this.region = region;
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

	/**
	 * @return the regions
	 */
	public List<String> getRegions()
	{
		return regions;
	}

	/**
	 * @param regions
	 *           the regions to set
	 */
	public void setRegions(final List<String> regions)
	{
		this.regions = regions;
	}

	/**
	 * @return the selectedRegion
	 */
	public String getSelectedRegion()
	{
		return selectedRegion;
	}

	/**
	 * @return the item
	 */
	public String getItem()
	{
		return item;
	}

	/**
	 * @param item
	 *           the item to set
	 */
	public void setItem(final String item)
	{
		this.item = item;
	}

	/**
	 * @param selectedRegion
	 *           the selectedRegion to set
	 */
	public void setSelectedRegion(final String selectedRegion)
	{
		this.selectedRegion = selectedRegion;
	}

	/**
	 * @return the media
	 */
	public String getMedia()
	{
		return media;
	}

	/**
	 * @param media
	 *           the media to set
	 */
	public void setMedia(final String media)
	{
		this.media = media;
	}

	@Override
	public String toString()
	{
		return "region: " + region + "result: " + result;
	}

	public String printRegions()
	{
		String regions = "Regions:";
		if (this.regions != null)
		{
			regions += "\n";
			for (final String cacheRegion : this.regions)
			{
				regions += cacheRegion + "\n";
			}
		}
		return regions;
	}
}
