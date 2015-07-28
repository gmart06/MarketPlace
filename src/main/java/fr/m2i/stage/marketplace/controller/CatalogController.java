package fr.m2i.stage.marketplace.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import fr.m2i.stage.marketplace.service.CatalogService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
	
	private CatalogService catalogService;

	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	@RequestMapping(value="/import", method=RequestMethod.GET)
	public String getImportCatalogForm() {
		return "merchant_space/import_catalog";
	}
	
	@RequestMapping(value="/import", method=RequestMethod.POST)
	public String addCatalog(@ModelAttribute("catalogueFile") MultipartFile catalogueFile, Model model) throws IllegalStateException, IOException {
		if(catalogueFile == null || catalogueFile.getSize() == 0) {
			// Error no submitted file
			model.addAttribute("errorFileType", "No transmitted file");
		} else if (catalogueFile.getSize() > 1048576) { //1Mio
			//Error file size too much
			model.addAttribute("errorFileType", "This file is too big, it must be less than 1Mio.");
		} else {		
			String directory = "C:\\Users\\Administrateur\\Desktop\\Java Spring\\temp\\";
			String filename = catalogueFile.getOriginalFilename();
			
			//if (filename.endsWith(".xml")) {
			if (filename.endsWith(".xml") && isAnXMLFile(catalogueFile.getInputStream())) {
				catalogueFile.transferTo(new File(directory + filename));
				model.addAttribute("success", "The file " + filename + " has been correctly received.");
			} else {
				// Error isn't an XML
				model.addAttribute("errorFileType", "Please, send a XML file.");
			}
			catalogueFile.getInputStream().close();
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
