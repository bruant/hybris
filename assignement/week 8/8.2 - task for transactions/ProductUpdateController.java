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
import org.training.facade.ProductUpdateFacade;
import org.training.model.ProductUpdateModel;


/**
 *
 * @author Peter Trestyanszki
 *
 */
@Controller
public class ProductUpdateController
{

	private static final Logger LOG = Logger.getLogger(ProductUpdateController.class);

	@Autowired
	private ProductUpdateFacade productUpdateFacade;

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String processGetRequest(final Model model)
	{
		model.addAttribute("update", new ProductUpdateModel());
		return "product-update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ProductUpdateModel processPostRequest(@RequestBody final ProductUpdateModel input)
	{
		LOG.info(input.toString());
		input.setResult(productUpdateFacade.updateManufacturerNameForProducts(input.getManufacturer(), input.getProductsAsList()));
		LOG.info(input.toString());
		return input;
	}

}
