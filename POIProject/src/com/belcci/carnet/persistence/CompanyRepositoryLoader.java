package com.belcci.carnet.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.belcci.carnet.config.ImageBox;
import com.belcci.carnet.model.Address;
import com.belcci.carnet.model.AddressType;
import com.belcci.carnet.model.Company;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Phone;

public class CompanyRepositoryLoader {
	private static final String TAG_COMPANY = "company";
	private org.w3c.dom.Document doc;
	private String filename;
	
	private static CompanyRepositoryLoader loader;
    
    private CompanyRepositoryLoader() {
    }
    
    public static CompanyRepositoryLoader getInstance(String filename) {
    	if (loader == null) {
    		loader = new CompanyRepositoryLoader(filename);
    	}
    	return loader;
    }

	private CompanyRepositoryLoader(String filename) {
		super();
		try {
			File fXmlFile = new File(filename);
			this.filename = filename;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CompanyRepository load() {
		CompanyRepository repository = CompanyRepository.getInstance();
		
		if (doc != null) {
			NodeList comps = doc.getElementsByTagName(TAG_COMPANY);

			for (int i = 0; i < comps.getLength(); i++) {
				Node comp = comps.item(i);
				NodeList tags = comp.getChildNodes();
				Party company = new Company();

				for (int y = 0; y < tags.getLength(); y++) {
					Node item = tags.item(y);

					if (item.getNodeType() == Element.ELEMENT_NODE) {
						String tagname = item.getNodeName();
						String tagvalue = item.getTextContent();
						System.out.println(tagname + tagvalue);

						if ("name".equals(tagname)) {
							company.setName(tagvalue);
						} else if ("unp".equals(tagname)) {
							company.setUnp(tagvalue);
						} else if ("phone".equals(tagname)) {
							Phone phone = new Phone();
							phone.setNumber(tagvalue);
							company.setPhone(phone);
						} else if ("fax".equals(tagname)) {
							Phone fax = new Phone();
							fax.setNumber(tagvalue);
							company.setFax(fax);
						} else if ("email".equals(tagname)) {
							company.setEmail(tagvalue);
						} else if ("trust".equals(tagname)) {
							company.setTrust(tagvalue);
						} else if ("address".equals(tagname)) {
							Address addr = getAddress(item);
							System.out.println(addr.toXML());
							company.addAddress(addr);
						}
					}

				}
				
				System.out.println(company.toXML());
				repository.addCompany(company);
			}
		}
		return repository;

	}

	private Address getAddress(Node node) {
		Address addr = new Address();
		NodeList tags = node.getChildNodes();

		for (int i = 0; i < tags.getLength(); i++) {
			Node item = tags.item(i);

			if (item.getNodeType() == Element.ELEMENT_NODE) {
				String tagname = item.getNodeName();
				String tagvalue = item.getTextContent();

				if ("country".equals(tagname)) {
					addr.setCountry(CountryList.getInstance().findCountryByName(tagvalue));
				} else if ("index".equals(tagname)) {
					addr.setIndex(tagvalue);
				} else if ("line".equals(tagname)) {
					addr.setLine(tagvalue);
				} else if ("house".equals(tagname)) {
					addr.setHouse(tagvalue);
				} else if ("office".equals(tagname)) {
					addr.setOffice(tagvalue);
				} else if ("city".equals(tagname)) {
					addr.setCity(tagvalue);
				} else if ("type".equals(tagname)) {
					addr.setType(convertStringToAddressType(tagvalue));
				}
			}
		}
		return addr;
	}

	private AddressType convertStringToAddressType(String tagvalue) {
		if ("WORK".equals(tagvalue)) {
			return AddressType.WORK;
		} else if ("HOME".equals(tagvalue)) {
			return AddressType.HOME;
		} else if ("MAIL".equals(tagvalue)) {
			return AddressType.WORK;
		}
		return AddressType.WORK;
	}

	public void save(CompanyRepository repository) {
          try {
              File file = new File(filename);
              Writer writer = new BufferedWriter(new FileWriter(file));
              writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <repository>");
              
              for (Party company: repository.getItems()) {
                  writer.write(company.toXML());
              }
              writer.write("</repository");
              writer.close();
            } catch ( IOException e ) {
                 e.printStackTrace();
            }
          
	}

}
