package by.traning.task08.service.builder;

import by.traning.task08.bean.Gem;
import by.traning.task08.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class SAXBuilder extends BaseBuilder {
    private static final Logger LOGGER = LogManager.getLogger(SAXBuilder.class.getName());
    private InputStream fileContent;

    public SAXBuilder(InputStream fileContent) {
        this.fileContent = fileContent;
    }

    public void buildGemList() throws ServiceException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.fatal(e);
        }
        try {
            saxParser.parse(fileContent, new DefaultHandler() {
                String lastElement;
                Attributes attributes;
                String name;
                String preciusnes;
                String origin;
                String color;
                double transparency;
                int edgesCount;
                double value;
                String date;
                String time;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    lastElement = qName;
                    this.attributes = attributes;
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String information = new String(ch, start, length);
                    information = information.replace("\n", "").trim();
                    if (attributes.getValue(0) != null) {
                        name = attributes.getValue(0);
                    }
                    if (!information.isEmpty()) {
                        if (lastElement.equals("preciousnes")) {
                            preciusnes = information;
                        }
                        if (lastElement.equals("origin")) {
                            origin = information;
                        }
                        if (lastElement.equals("color")) {
                            color = information;
                        }
                        if (lastElement.equals("transparency")) {
                            transparency = Double.parseDouble(information);
                        }
                        if (lastElement.equals("edgesCount")) {
                            edgesCount = Integer.parseInt(information);
                        }

                        if (lastElement.equals("value")) {
                            value = Double.parseDouble(information);
                        }

                        if (lastElement.equals("date")) {
                            date = information;
                        }

                        if (lastElement.equals("time")) {
                            time = information;
                        }

                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (name != null && preciusnes != null && origin != null && color != null && transparency != 0 && edgesCount != 0 && value != 0 && date != null && time != null) {
                        gemList.add(new Gem(name, preciusnes, origin, color, transparency, edgesCount, value, date,
                                time));
                        name = null;
                        preciusnes = null;
                        origin = null;
                        color = null;
                        transparency = 0;
                        edgesCount = 0;
                        value = 0;
                        date = null;
                        time = null;
                    }
                }
            });
        } catch (SAXException | IOException e) {
            LOGGER.fatal(e);
            throw new ServiceException("SAXBuildException", e);
        }
    }
}
