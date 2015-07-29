package fr.m2i.stage.marketplace.importCatalog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.m2i.stage.marketplace.domain.entity.Catalog;
import fr.m2i.stage.marketplace.domain.entity.Delivery;
import fr.m2i.stage.marketplace.domain.entity.Product;
import fr.m2i.stage.marketplace.domain.entity.ProductDetail;

public class ParseXML {

	private static final Logger logger = LoggerFactory.getLogger(ParseXML.class);
	private List<ErrorXML> errors = new ArrayList<>();
	
	
	
	public List<ErrorXML> getErrors() {
		return errors;
	}

//	public static void main(String[] args) throws URISyntaxException, FileNotFoundException, XMLStreamException {
//		FileInputStream fis = null;
//		URL resource = App.class.getResource("catalogue.xml");
//		File file = new File(resource.toURI());
//
//		try {
//
//			fis = new FileInputStream(file);
//			ParseXML p = new ParseXML();
//			
//			
//			System.out.println(p.readFromXMLProduct(fis));
//			
//			for (ErrorXML e : p.getErrors()) {
//				System.out.println("line " + e.getLine() + " : " + e.getMessage());
//			}
//			
//		} catch (ArrayIndexOutOfBoundsException aioobe) {
//
//			System.exit(0);
//		}
//	
//	}

	public Catalog readFromXMLProduct(FileInputStream is)
			throws XMLStreamException, URISyntaxException, FileNotFoundException {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;

		try {

			reader = inputFactory.createXMLStreamReader(is);

			skipCommentsAndSpaces(reader);
			return readCatalog(reader);

		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
	}
	
	
	private Catalog readCatalog(XMLStreamReader reader) throws XMLStreamException {
		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Catalog structure is FALSE"));
		}
		
		if (!"catalog".equals(reader.getLocalName())) {
		
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start xml is FALSE"));
		}

		logger.info("<catalog>");

		Catalog catalog = new Catalog();
		
			
			if("catalog".equals(reader.getLocalName())) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (reader.getAttributeValue(null, "date") != null){
					
					
				}
				String date = reader.getAttributeValue(null, "date");
				
				if (date.isEmpty()){
					errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Date attribute is not indicate"));
				} else{
				
					try {
						catalog.setCreationDate(sf.parse(date));
					} catch (ParseException e) {
						errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Date attribute is FALSE"));
			
					}
				}
				skipCommentsAndSpaces(reader);
				catalog.setProducts(new HashSet<>(readProducts(reader)));
			} else if ( XMLStreamReader.END_ELEMENT == reader.getEventType() && "catalog".equals(reader.getLocalName())) {
				logger.info("\t </catalog>");
				
			} else {
				
				errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "catalog attribute is FALSE"));	
			}
			return catalog;
	}

	private List<Product> readProducts(XMLStreamReader reader) throws XMLStreamException {
		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start products attribute is FALSE"));
		}
		
		if (!"products".equals(reader.getLocalName())) {
		
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "products attribute is FALSE"));
		}
		
		logger.info("\t <" + reader.getLocalName() + ">");

		List<Product> products = new ArrayList<Product>();

		while(true) {
			skipCommentsAndSpaces(reader);
			if(XMLStreamReader.START_ELEMENT == reader.getEventType() && "product".equals(reader.getLocalName())) {
				products.add(readProduct(reader));	
			} else if ( XMLStreamReader.END_ELEMENT == reader.getEventType() && "products".equals(reader.getLocalName())) {
				logger.info("\t </products>");
				return products;
			} else {
			
				errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "products attribute is FALSE"));
			}
		}

	}

	private Product readProduct(XMLStreamReader reader) throws XMLStreamException {
		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start product attribute is FALSE"));
		}
		
		if (!"product".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "product attribute is FALSE"));
		
		}
		
		logger.info("\t <" + reader.getLocalName() + ">");

		Product product = new Product();

		String productRefAttribute = reader.getAttributeValue(null, "ref");
		if (productRefAttribute == null) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Ref attribute of product tag is FALSE"));
		}

		product.setReference(reader.getAttributeValue(null, "ref"));
		skipCommentsAndSpaces(reader);
		product.setTitle(readTitle(reader));
		skipCommentsAndSpaces(reader);
		product.setDescription(readDescription(reader));
		skipCommentsAndSpaces(reader);
		//a tester avec la db
		//product.setCategory(readCategorie(reader));
		skipCommentsAndSpaces(reader);
		product.setListProductDetail(new HashSet<>( readReferences(reader)));

		skipCommentsAndSpaces(reader);
		if (reader.getEventType() != XMLStreamReader.END_ELEMENT || !"product".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "End product tag : " 
																			+ reader.getLocalName()
																			+ "is FALSE "));
		}

		logger.info("\t </product>");

		return product;
	}

	private String readTitle(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "title");
	}

	private String readDescription(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "description");
	}

	private int readCategorie(XMLStreamReader reader) throws XMLStreamException {
		try {
			return Integer.parseInt(readSimpleElement(reader, "categorie"));
		} catch (NumberFormatException e) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "categorie attribute of product tag is FALSE"));
			return 0;
			
		}
	}

	private List<ProductDetail> readReferences(XMLStreamReader reader) throws XMLStreamException {

		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start references attribute is FALSE"));
		}
		
		if (!"references".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "references attribute is FALSE"));
			
		}
		
		logger.info("\t <" + reader.getLocalName() + ">");

		List<ProductDetail> references = new ArrayList<ProductDetail>();

		while(true) {
			skipCommentsAndSpaces(reader);
			if(XMLStreamReader.START_ELEMENT == reader.getEventType() && "reference".equals(reader.getLocalName())) {
				references.add(readReference(reader));	
			} else if ( XMLStreamReader.END_ELEMENT == reader.getEventType() && "references".equals(reader.getLocalName())) {
				logger.info("\t </references>");
				return references;
			} else {
				errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "references attribute is FALSE"));
			}
		}

	}

	private ProductDetail readReference(XMLStreamReader reader) throws XMLStreamException {
		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start reference attribute is FALSE"));
		}
		
		if (!"reference".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Reference attribute is FALSE"));
		}
		
		logger.info("\t <" + reader.getLocalName() + ">");

		ProductDetail productDetail = new ProductDetail();

		skipCommentsAndSpaces(reader);
		productDetail.setPrice(readPrice(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setEcotax(readEcotax(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setSku(readRefDescr(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setEan(readEan(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setColor(readColor(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setSize(readSize(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setWeight(readWeight(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setImage_url(readImage(reader));
		skipCommentsAndSpaces(reader);
		productDetail.setDeliveries(new HashSet<>(readDeliveries(reader)));
		
		skipCommentsAndSpaces(reader);
		if (reader.getEventType() != XMLStreamReader.END_ELEMENT || !"reference".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "End reference attribute is FALSE"));
		}

		logger.info("\t </reference>");

		return productDetail;
	}

	private List<Delivery> readDeliveries(XMLStreamReader reader) throws XMLStreamException {

		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start livraisons attribute is FALSE"));
		}
		
		if (!"livraisons".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Livraisons attribute is FALSE"));
		}
		
		logger.info("\t <" + reader.getLocalName() + ">");

		List<Delivery> deliveries = new ArrayList<Delivery>();

		while(true) {
			skipCommentsAndSpaces(reader);
			if(XMLStreamReader.START_ELEMENT == reader.getEventType() && "livraison".equals(reader.getLocalName())) {
				deliveries.add(readDelivery(reader));	
			} else if ( XMLStreamReader.END_ELEMENT == reader.getEventType() && "livraisons".equals(reader.getLocalName())) {
				logger.info("\t </livraisons>");
				return deliveries;
			} else {
				errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "livraisons attribute is FALSE"));
			}
		}

	}

	/**
	 * Must be called with reader eventType == START_ELEMENT AND reader.getLocalName() == "livraison"
	 * @param reader
	 * @return
	 * @throws XMLStreamException
	 */
	private Delivery readDelivery(XMLStreamReader reader) throws XMLStreamException {
		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Start livraison attribute is FALSE"));
		}
		
		if (!"livraison".equals(reader.getLocalName())) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Livraison attribute is FALSE"));
		}
		
		logger.info("\t <" + reader.getLocalName() + ">");

		Delivery delivery = new Delivery();
		if (reader.getAttributeValue(null, "type").isEmpty()){
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "type attribute is not indicate"));
		}
		delivery.setName(reader.getAttributeValue(null, "type"));
		if (reader.getAttributeValue(null, "delai").isEmpty()){
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "delai attribute is not indicate"));
		} else {
		int time = Integer.parseInt(reader.getAttributeValue(null, "delai"));
		
		String unit = reader.getAttributeValue(null, "unit");
			if ("H".equals(unit)) {
				time = time / 24;
			} else if ("W".equals(unit)) {
				time = time * 7;
			} else if ("M".equals(unit)) {
				time = time * 30;
			} else if ("D".equals(unit)) {
				
			} else {
				errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "unit attribute is FALSE : " + unit));
			}
		delivery.setDelay(time);
		}
		if (reader.getAttributeValue(null, "prix").isEmpty()){
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "prix attribute is not indicate"));
		}
		delivery.setFees(Double.parseDouble(reader.getAttributeValue(null, "prix")));

		skipCommentsAndSpaces(reader);
		
		if (reader.getEventType() != XMLStreamReader.END_ELEMENT
				|| ! "livraison".equals(reader.getLocalName())) {
			
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "End livraison attribute is FALSE"));
	}

		logger.info("\t </livraison>");

		return delivery;
	}

	// *******************************************************************************
	// *******************************************************************************

	private double readPrice(XMLStreamReader reader) throws XMLStreamException {
		try {
			return Double.parseDouble(readSimpleElement(reader, "price"));
		} catch (NumberFormatException e) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Price attribute is not indicate"));
			return 0.0;
		}
	}

	private double readEcotax(XMLStreamReader reader) throws XMLStreamException {
		try {
			return Double.parseDouble(readSimpleElement(reader, "ecotax"));
		} catch (NumberFormatException e) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "Ecotax attribute is not indicate"));
			return 0;
		}
	}

	private String readRefDescr(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "description_reference");
	}

	private String readEan(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "ean");
	}

	private String readColor(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "color");
	}

	private String readSize(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "size");
	}

	private int readWeight(XMLStreamReader reader) throws XMLStreamException {
		try {
			return Integer.parseInt(readSimpleElement(reader, "weight"));
		} catch (NumberFormatException e) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "weight attribute is not indicate"));
			return 0;
		
		}
	}

	private String readImage(XMLStreamReader reader) throws XMLStreamException {
		return readSimpleElement(reader, "image");
	}

	/**
	 * Reads a simple XLM element <elementNameExpected>some
	 * content</elementNameExpected>
	 * 
	 * @param reader
	 * @param elementNameExpected
	 * @return
	 * @throws XMLStreamException
	 * @throws {@link
	 *             NullPointerException} if elementNameExpected is null
	 */
	private String readSimpleElement(XMLStreamReader reader, String elementNameExpected) throws XMLStreamException {
		Objects.requireNonNull(elementNameExpected);

		if(XMLStreamReader.START_ELEMENT != reader.getEventType()) {
			throw new RuntimeException("expecting START_ELEMENT but found " + reader.getEventType());
		}
		
		if (!elementNameExpected.equals(reader.getLocalName())) {
			throw new RuntimeException("<" + elementNameExpected +"> expected but found " + reader.getLocalName());
		}

		logger.info("\t <" + reader.getLocalName() + ">");

		if (!elementNameExpected.equals(reader.getLocalName())) {
			throw new RuntimeException(
					elementNameExpected + " start_element expected but found " + reader.getLocalName());
		}

		String content = readCharacters(reader);

		if (reader.next() != XMLStreamReader.END_ELEMENT || !elementNameExpected.equals(reader.getLocalName())) {
			throw new RuntimeException(
					elementNameExpected + " end_element expected but found " + reader.getLocalName());
		}

		logger.info("\t </" + reader.getLocalName() + ">");
		return content;

	}

	// **********************************************************************************

	private String readCharacters(XMLStreamReader reader) throws XMLStreamException {
		int eventType = reader.next(); // CHARACTERS
		if (!(XMLStreamReader.CHARACTERS == eventType)) {
			errors.add(new ErrorXML(reader.getLocation().getLineNumber(), "No value indicated"));
		}

		return reader.getText();
	}
	
	private void skipCommentsAndSpaces(XMLStreamReader reader)  throws XMLStreamException {
		while(true) {
			int eventType = reader.next();
			if (XMLStreamReader.COMMENT == eventType 
					|| XMLStreamReader.SPACE == eventType 
					|| (XMLStreamReader.CHARACTERS == eventType && reader.isWhiteSpace() ) ) { 
				// skip COMMENT and SPACE
			} else {
				return;
			}
		}		
	}

}
