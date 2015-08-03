package fr.m2i.stage.marketplace.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import fr.m2i.stage.marketplace.domain.entity.Catalog;
import fr.m2i.stage.marketplace.domain.entity.Merchant;
import fr.m2i.stage.marketplace.exception.ResourceNotFoundException;
import fr.m2i.stage.marketplace.importCatalog.ParseXML;
import fr.m2i.stage.marketplace.service.CatalogService;
import fr.m2i.stage.marketplace.service.MerchantService;


@Controller
@RequestMapping("/merchant/{idMerchant}/catalog")
public class CatalogController {

	private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

	private MerchantService merchantService;
	private CatalogService catalogService;

	private ParseXML parser;

	@Autowired
	public CatalogController(MerchantService merchantService, CatalogService catalogService, ParseXML parser) {
		this.merchantService = merchantService;
		this.catalogService = catalogService;
		this.parser = parser;
	}

	@RequestMapping(value="/import", method=RequestMethod.GET)
	public String getImportCatalogForm(@PathVariable Long idMerchant) {
		if (! merchantService.hasMerchant(idMerchant)) {
			throw new ResourceNotFoundException("No merchant with this id.");
		}
		return "merchant_space/import_catalog";
	}

	@RequestMapping(value="/import", method=RequestMethod.POST)	
	public String addCatalog(@PathVariable Long idMerchant, @ModelAttribute("catalogFile") MultipartFile catalogFile, Model model)  throws IllegalStateException, IOException {
		Merchant merchant = merchantService.findByIdWithAllCatlalog(idMerchant);

		if (merchant == null) {
			throw new ResourceNotFoundException("No merchant with this id.");
		}

		if(catalogFile == null || catalogFile.getSize() == 0) {
			// Error no submitted file
			model.addAttribute("errorFileType", "No transmitted file");
			return "merchant_space/import_catalog";
		} else if (catalogFile.getSize() > 1048576) { //1Mio
			//Error file size too much
			model.addAttribute("errorFileType", "This file is too big, it must be less than 1Mio.");
			return "merchant_space/import_catalog";
		} else {
			String filename = catalogFile.getOriginalFilename();

			if (filename.endsWith(".xml") && isAnXMLFile(catalogFile.getInputStream())) {
				File catalogXMLFile = File.createTempFile(filename, null);
				catalogFile.transferTo(catalogXMLFile);
				
				logger.info("Temp file : " + catalogXMLFile.getAbsolutePath());

				catalogXMLFile.deleteOnExit();
				Catalog catalog = null;
				try {
					catalog = parser.readFromXMLProduct(new FileInputStream(catalogXMLFile));
				} catch (XMLStreamException | URISyntaxException e) {
					logger.warn("The file can't be open.", e);
					model.addAttribute("errorFileType", "The file can't be open.");
					return "merchant_space/import_catalog";
				}

				if(parser.hasErrors()) {
					model.addAttribute("errorXMLTitle", "Errors list in your XML files");
					model.addAttribute("errorXML", parser.getErrors());
					return "merchant_space/import_catalog";
				}

				// Permit to place reference to all entities (example: catalog in product and product in product detail)
				ParseXML.addAllDependencies(catalog);

				if(merchant.hasProductInCatalog()) {
					catalogService.deleteCatalog(merchant.getCatalog());
				} 

				catalog.setMerchant(merchant);
				merchant.setCatalog(catalog);
				merchantService.add(merchant);

				model.addAttribute("success", "You have correctly added " + catalog.getProducts().size() + " products in your catalog.");
			} else {
				// Error isn't an XML
				model.addAttribute("errorFileType", "Please, send a XML file.");
				return "merchant_space/import_catalog";
			}


		}
		return "merchant_space/import_catalog";
	}

	private boolean isAnXMLFile(InputStream is) {
		boolean isXmlFile = true;
		try (InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr)) {
			String firstLine = br.readLine();
			if (! firstLine.contains("<?xml")) {
				isXmlFile = false;
			}
		} catch (IOException e) {
			isXmlFile =  false;
		} 

		return isXmlFile;
	}

}
