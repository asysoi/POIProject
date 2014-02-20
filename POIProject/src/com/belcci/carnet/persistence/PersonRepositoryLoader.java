package com.belcci.carnet.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.belcci.carnet.model.Address;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Passport;
import com.belcci.carnet.model.Person;
import com.belcci.carnet.model.Phone;

public class PersonRepositoryLoader {
	private static final String TAG_PERSON = "person";
	private org.w3c.dom.Document doc;
	private String filename;
	
	private static PersonRepositoryLoader loader;
    
    private PersonRepositoryLoader() {
    }
    
    public static PersonRepositoryLoader getInstance(String filename) {
    	if (loader == null) {
    		loader = new PersonRepositoryLoader(filename);
    	}
    	return loader;
    }

	private PersonRepositoryLoader(String filename) {
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

	public PersonRepository load() {
		PersonRepository repository = PersonRepository.getInstance();
		
		if (doc != null) {
			NodeList comps = doc.getElementsByTagName(TAG_PERSON);

			for (int i = 0; i < comps.getLength(); i++) {
				Node comp = comps.item(i);
				NodeList tags = comp.getChildNodes();
				Person person = new Person();

				for (int y = 0; y < tags.getLength(); y++) {
					Node item = tags.item(y);

					if (item.getNodeType() == Element.ELEMENT_NODE) {
						String tagname = item.getNodeName();
						String tagvalue = item.getTextContent();
						System.out.println(tagname + tagvalue);

						if ("firstname".equals(tagname)) {
							person.setFirstname(tagvalue);
						} else if ("lastname".equals(tagname)) {
							person.setLastname(tagvalue);
						} else if ("middlename".equals(tagname)) {
							person.setMiddlename(tagvalue);
						} else if ("unp".equals(tagname)) {
							person.setUnp(tagvalue);
						} else if ("phone".equals(tagname)) {
							Phone phone = new Phone();
							phone.setNumber(tagvalue);
							person.setPhone(phone);
						} else if ("fax".equals(tagname)) {
							Phone fax = new Phone();
							fax.setNumber(tagvalue);
							person.setFax(fax);
						} else if ("email".equals(tagname)) {
							person.setEmail(tagvalue);
						} else if ("trust".equals(tagname)) {
							person.setTrust(tagvalue);
						} else if ("address".equals(tagname)) {
							Address addr = getAddress(item);
							System.out.println(addr.toXML());
							person.addAddress(addr);
						} else if ("passport".equals(tagname)) {
							Passport passport = getPassport(item);
							System.out.println(passport);
							((Person)person).setPassport(passport);
						}
					}

				}
				
				System.out.println(person.toXML());
				repository.addPerson(person);
			}
		}
		return repository;

	}

	private Passport getPassport(Node node) {
		Passport passport = new Passport();
		NodeList tags = node.getChildNodes();

		for (int i = 0; i < tags.getLength(); i++) {
			Node item = tags.item(i);

			if (item.getNodeType() == Element.ELEMENT_NODE) {
				String tagname = item.getNodeName();
				String tagvalue = item.getTextContent();

				if ("personalid".equals(tagname)) {
					passport.setPersonalid(tagvalue);
				} else if ("code".equals(tagname)) {
					passport.setCode(tagvalue);
				} else if ("number".equals(tagname)) {
					passport.setNumber(tagvalue);
				} else if ("issdate".equals(tagname)) {
					passport.setIssdate(convertStringToCalendar(tagvalue));
				} else if ("validdate".equals(tagname)) {
					passport.setIssdate(convertStringToCalendar(tagvalue));
				} else if ("issued".equals(tagname)) {
					passport.setIssued(tagvalue);
				} else if ("eissued".equals(tagname)) {
					passport.setEissued(tagvalue);
				}
			}
		}
		return passport;
	}

	private Calendar convertStringToCalendar(String tagvalue) {
		Calendar cal = GregorianCalendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	    try {
	      cal.setTime(sdf.parse(tagvalue));
	    } catch (Exception ex) {
	      ex.printStackTrace(); 	
	      cal = null; 	
	    }
        return cal;
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
				}
			}
		}
		return addr;
	}

	public void save(PersonRepository repository) {
          try {
              File file = new File(filename);
              Writer writer = new BufferedWriter(new FileWriter(file));
              writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <repository>");
              
              for (Party company: repository.getItems() ) {
                  writer.write(company.toXML());
              }
              writer.write("</repository");
              writer.close();
            } catch ( IOException e ) {
                 e.printStackTrace();
            }
          
	}

}
