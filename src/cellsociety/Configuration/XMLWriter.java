package cellsociety.Configuration;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import cellsociety.Visualization.DialogBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class XMLWriter {
   private static DialogBox dialogBox;
    public static void main(String[] args) {

            DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder icBuilder;
            try {
                icBuilder = icFactory.newDocumentBuilder();
                Document doc = icBuilder.newDocument();
                Element mainRootElement;
                mainRootElement = doc.createElement("simulation");
                mainRootElement.setAttribute("type", "Fire");
                doc.appendChild(mainRootElement);
                mainRootElement.appendChild(getCompany(doc ,dialogBox.getSimulationConfig().getTitle(), dialogBox.getSimulationConfig().getShape() ,dialogBox.getSimulationConfig().getShape()));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult console = new StreamResult(System.out);
                transformer.transform(source, console);

                System.out.println("\nXML DOM Created Successfully..");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static Node getCompany(Document doc, String name, String age, String role) {
            Element company = doc.createElement("Company");

            company.appendChild(getCompanyElements(doc, company, "Name", name));
            company.appendChild(getCompanyElements(doc, company, "Type", age));
            company.appendChild(getCompanyElements(doc, company, "Employees", role));
            return company;
        }

        // utility method to create text node
        private static Node getCompanyElements(Document doc, Element element, String name, String value) {
            Element node = doc.createElement(name);
            node.appendChild(doc.createTextNode(value));
            return node;
        }
    }

