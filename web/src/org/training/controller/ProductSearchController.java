/**
 *
 */
package org.training.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.facade.ProductSearchFacade;
import org.training.model.ProductSearchModel;


/**
 * Controller to process product search related queries.
 *
 * @author Peter Trestyanszki
 *
 */
@Controller
public class ProductSearchController
{

	private static final Logger LOG = Logger.getLogger(ProductSearchController.class);

	@Autowired
	private ProductSearchFacade facade;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String processGetRequest(final Model model)
	{
		model.addAttribute("search", new ProductSearchModel());
		return "product-search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ProductSearchModel processPostRequest(@RequestBody final ProductSearchModel input)
	{
		LOG.info(input.toString());
		input.setProduct(facade.searchByCodeInCatalog(input.getCode(), input.getCatalog(), input.getVersion()));
		LOG.info(input.toString());
		return input;
	}

}
