package cellsociety.Configuration;
import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import cellsociety.ProbConstant;
import cellsociety.Simulation;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/**
 * @Author-Himanshu Jain
 * This class is for the Fire Configuration. It sets all the values for the Fire Simulation using the setter methods
 * in the Configuration file
 */
public class FireWriter {
    private static String conc="";

    /**
     *
     * @param simulationConfig
     * @param myNewProbCatch
     * @param mySim
     * @param s
     */
    public static void main(Configuration simulationConfig, Map<Slider, ProbConstant> myNewProbCatch, Simulation mySim, String s) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement;
            double probcatch=0;
            for(ProbConstant c:myNewProbCatch.values()){
                probcatch=c.getMyProbCatch();
            }
            mainRootElement = doc.createElement("simulation");
            Map<String, Integer> concentrationMap= mySim.getTypesOfCells();
            ArrayList<Integer> numberofEach= new ArrayList<>();
            for(String typeOfCell:concentrationMap.keySet()){
                numberofEach.add(concentrationMap.get(typeOfCell));
            }
            Integer type1;
            Integer type2;
            Float per1;
            Float per2;
            type1=numberofEach.get(0);
            type2=numberofEach.get(1);
            per1= (((float)type1/(float)(type1+type2)));
            per2= 1-per1;
            conc=per1+","+per2;
            mainRootElement.setAttribute("type", simulationConfig.getTitle());
            doc.appendChild(mainRootElement);
            mainRootElement.appendChild(getCompany(doc,simulationConfig.getTitle(), simulationConfig, probcatch));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(new File("resources/"+s+".xml"));
            transformer.transform(source, console);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"File Error").showAndWait();
        }
    }

    /**
     *
     * @param doc
     * @param title
     * @param simulation
     * @param prob
     * @return
     */
    private static Node getCompany(Document doc, String title, Configuration simulation, double prob) {
        Element company = doc.createElement("gridlayout");
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(0), title));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(1), String.valueOf(simulation.getMaxStates())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(2), String.valueOf(simulation.getRows())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(3), String.valueOf(simulation.getColumns())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(4), String.valueOf(simulation.getLeft())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(5), String.valueOf(simulation.getRight())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(6), String.valueOf(simulation.getTop())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(7), String.valueOf(simulation.getBottom())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(8), String.valueOf(simulation.getNeighbours())));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(9), String.valueOf(prob)));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(10), simulation.getType1()));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(11),simulation.getType2()));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(12), simulation.getType3()));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(13), simulation.getProbCatchLabel().get(0)));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(14), String.valueOf(simulation.getMaxProb().get(0))));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(15), simulation.getNeighPatterninString()));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(16), simulation.getShape()));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(17), conc));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(18), "Given"));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(19), simulation.getColors().get(0)));
        company.appendChild(getCompanyElements(doc, company, Fire.DATA_FIELDS.get(20), simulation.getMyboundary()));
        return company;
    }

    /**
     *
     * @param doc
     * @param element
     * @param name
     * @param value
     * @return
     */
    private static Node getCompanyElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


}
