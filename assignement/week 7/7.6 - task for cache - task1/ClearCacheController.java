/**
 *
 */
package org.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.regioncache.key.impl.GenerationalCacheDelegate;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.regioncache.region.CacheRegionProvider;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Utilities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.model.ClearCacheModel;


/**
 * Controller to clear caches.
 *
 * @author Peter Trestyanszki
 *
 */
@Controller
public class ClearCacheController
{

	private static final Logger LOG = Logger.getLogger(ClearCacheController.class);

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CacheRegionProvider cacheRegionProvider;

	@Resource
	private GenerationalCacheDelegate generationalCacheDelegate;

	private List<CacheRegion> regions;

	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public String processGetRequest(final Model model)
	{
		model.addAttribute("cacheModel", new ClearCacheModel());
		return "clear-cache";
	}

	@RequestMapping(value = "/getAllCacheRegions", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getAllCacheRegions()
	{
		return getRegions();
	}

	@RequestMapping(value = "/cache", method = RequestMethod.POST)
	public String processPostRequest(@RequestBody final ClearCacheModel model)
	{
		final String regionToClear = model.getSelectedRegion();
		LOG.info("Clear cache: " + regionToClear);

		switch (regionToClear)
		{
			case "all":
				clearAllRegionCache();
				break;
			default:
				clearCacheByName(regionToClear);
				break;
		}
		return "clear-cache";
	}

	@RequestMapping(value = "/cache/key", method = RequestMethod.POST)
	public String invalidate(@RequestBody final ClearCacheModel model)
	{

		final String item = model.getItem();

		if (!StringUtils.isEmpty(item))
		{
			final ProductModel exampleModel = new ProductModel();
			exampleModel.setCode(item);

			final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("Default", "Staged");
			catalogVersionService.addSessionCatalogVersion(catalogVersion);

			final List<ProductModel> products = flexibleSearchService.getModelsByExample(exampleModel);
			for (final ProductModel productModel : products)
			{
				Utilities.invalidateCache(productModel.getPk());
			}
		}

		final String media = model.getMedia();

		if (!StringUtils.isEmpty(media))
		{
			final MediaModel exampleModel = new MediaModel();
			exampleModel.setCode(media);
			final List<MediaModel> models = flexibleSearchService.getModelsByExample(exampleModel);
			for (final MediaModel mediaModel : models)
			{
				Utilities.invalidateCache(mediaModel.getPk());
			}

		}

		return "clear-cache";
	}

	private void clearCacheByName(final String name)
	{
		final List<CacheRegion> caches = cacheRegionProvider.getRegionByName(name);
		for (final CacheRegion cacheRegion : caches)
		{
			cacheRegion.clearCache();
		}
	}

	private void clearAllRegionCache()
	{
		for (final CacheRegion cacheRegion : regions)
		{
			cacheRegion.clearCache();
		}
	}


	private List<String> getRegions()
	{
		final List<String> regs = new ArrayList<>();

		if (regions == null)
		{
			regions = cacheRegionProvider.getAllRegions();
		}

		if (regions != null)
		{
			for (final CacheRegion cacheRegion : regions)
			{
				regs.add(cacheRegion.getName());
			}
		}
		return regs;
	}

}
