package cellsociety.Configuration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static cellsociety.Visualization.DialogBox.*;

public class Reader {
    // Readable error message that can be displayed by the GUI
    public static final String ERROR_MESSAGE = "XML file does not represent %s";
    // name of root attribute that notes the type of file expecting to parse
    private final String TYPE_ATTRIBUTE;
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private final DocumentBuilder DOCUMENT_BUILDER;
    private static Map<String, String> results;


    /**
     * Create parser for XML files of given type.
     */
    public Reader(String type) {
        DOCUMENT_BUILDER = getDocumentBuilder();
        TYPE_ATTRIBUTE = type;
    }

        /**
     * Get data contained in this XML file as an object
     */
    public Map<String, String> getSimulation (String name,File dataFile) {
        //results.clear();
        Element root = getRootElement(dataFile);
        if(name.equals(FIRE_FILE)){
            forFire(root);
        }
        else if(name.equals(GAME_FILE){
           forGame(root);
        }
        else if(name.equals(SEG_FILE)){
            forSeg(root);
        }
        else if(name.equals(PREY_FILE)){
          forPrey(root);
        }
        else if(name.equals(PERC_FILE)){
            forPercolation(root);
        }
        else if(name.equals(RPS_FILE)){
            forRps(root);
        }

        return results;
    }

    private void forRps(Element root) {
        if (! isValidFile(root, Rps.DATA_TYPE)) {
            throw new FileInputException(ERROR_MESSAGE, Rps.DATA_TYPE);
        }
        results = new HashMap<>();
        for (String field : Rps.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
    }

    private void forPercolation(Element root) {
        if (! isValidFile(root, Percolation.DATA_TYPE)) {
            throw new FileInputException(ERROR_MESSAGE, Percolation.DATA_TYPE);
        }
        results = new HashMap<>();
        for (String field : Percolation.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));

        }
    }

    private void forFire(Element root) {

        if (! isValidFile(root, Fire.DATA_TYPE)) {

            throw new FileInputException(ERROR_MESSAGE, Fire.DATA_TYPE);
        }
        results = new HashMap<>();
        for (String field : Fire.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));


        }
    }
    private void forSeg(Element root) {
        if (! isValidFile(root, Segregation.DATA_TYPE)) {
            throw new FileInputException(ERROR_MESSAGE, Segregation.DATA_TYPE);
        }
        results = new HashMap<>();
        for (String field : Segregation.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
    }
    private void forPrey(Element root) {
        if (! isValidFile(root, Prey.DATA_TYPE)) {
            throw new FileInputException(ERROR_MESSAGE, Prey.DATA_TYPE);
        }
        results = new HashMap<>();
        for (String field : Prey.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
    }
    private void forGame(Element root) {
        if (! isValidFile(root, Game.DATA_TYPE)) {
            throw new FileInputException(ERROR_MESSAGE, Game.DATA_TYPE);
        }
        results = new HashMap<>();
        for (String field : Game.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
    }
    // get root element of an XML file
        private Element getRootElement (File xmlFile) {
            try {
                DOCUMENT_BUILDER.reset();
                Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);

                return xmlDocument.getDocumentElement();
            }
            catch (SAXException | IOException e) {
                throw new FileInputException(e);
            }
        }

        // returns if this is a valid XML file for the specified object type
        private boolean isValidFile (Element root, String type) {
            return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
        }

        // get value of Element's attribute
        private String getAttribute (Element e, String attributeName) {
            return e.getAttribute(attributeName);
        }

        // get value of Element's text
        private String getTextValue (Element e, String tagName) {
            NodeList nodeList = e.getElementsByTagName(tagName);
            if (nodeList != null && nodeList.getLength() > 0) {
                return nodeList.item(0).getTextContent();
            }
            else {
                // FIXME: empty string or exception? In some cases it may be an error to not find any text
                return "";
            }
        }

        // boilerplate code needed to make a documentBuilder
        private DocumentBuilder getDocumentBuilder () {
            try {
                return DocumentBuilderFactory.newInstance().newDocumentBuilder();
            }
            catch (ParserConfigurationException e) {
                throw new FileInputException(e);
            }
        }
}

