package by.traning.task08.service.builder;

import by.traning.task08.service.util.impl.StringToLocalDateConverter;
import by.traning.task08.service.util.impl.StringToLocalTimeConverter;
import by.traning.task08.bean.Gem;
import by.traning.task08.bean.Preciousnes;
import by.traning.task08.bean.VisualParameter;
import by.traning.task08.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class StAXBuilder extends BaseBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    InputStream fileContent;
    public StAXBuilder(InputStream fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public void buildGemList() throws ServiceException {
        Gem gem = null;
        VisualParameter visualParameter = null;
        LocalDate date = null;
        LocalTime time = null;

        File file = new File(DOMBuilder.class.getResource("/Gems.xml").getPath());
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = null;
        try {
            streamReader = factory.createXMLStreamReader(fileContent);
        } catch (XMLStreamException e) {
            LOGGER.fatal(e);
            throw new ServiceException("StAXBuildException", e);
        }
        while (true) {
            try {
                if (!streamReader.hasNext()) {
                    break;
                }
                streamReader.next();
            } catch (XMLStreamException e) {
                LOGGER.fatal(e);
            }
            if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                if(streamReader.getLocalName().equals("gem")) {
                    gem = new Gem();
                    visualParameter = new VisualParameter();
                    if(streamReader.getAttributeCount() > 0) {
                        String name = streamReader.getAttributeValue(null,"name");
                        gem.setName(name);
                    }
                }
                try {
                    if (streamReader.getLocalName().equals("preciousnes")) {
                        gem.setPreciousnes(Preciousnes.valueOf(streamReader.getElementText()));
                    }
                    if (streamReader.getLocalName().equals("origin")) {
                        gem.setOrigin(streamReader.getElementText());
                    }
                    if (streamReader.getLocalName().equals("color")) {
                        visualParameter.setColor(streamReader.getElementText());
                    }
                    if (streamReader.getLocalName().equals("transparency")) {
                        visualParameter.setTransparency(Double.parseDouble(streamReader.getElementText()));
                    }
                    if (streamReader.getLocalName().equals("edgesCount")) {
                        visualParameter.setEdgesCount(Integer.parseInt(streamReader.getElementText()));
                    }
                    if (streamReader.getLocalName().equals("value")) {
                        gem.setValue(Double.parseDouble(streamReader.getElementText()));
                    }
                    if (streamReader.getLocalName().equals("date")) {
                        date = StringToLocalDateConverter.getInstance().convert(streamReader.getElementText());
                    }
                    if (streamReader.getLocalName().equals("time")) {
                        time = StringToLocalTimeConverter.getInstance().convert(streamReader.getElementText());
                    }
                } catch (XMLStreamException e) {
                    LOGGER.fatal(e);
                    throw new ServiceException("StAXBuildException", e);
                }
            }
            if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT) {
                if(streamReader.getLocalName().equals("gem")) {
                    gem.setVisualParameter(visualParameter);
                    gem.setDate(date);
                    gem.setTime(time);
                    gemList.add(gem);
                }
            }
        }
    }
}
