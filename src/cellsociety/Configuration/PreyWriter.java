package cellsociety.Configuration;
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
public class PreyWriter {
    private static String conc="";

    /**
     *
     * @param simulationConfig
     * @param myNewProbCatch
     * @param mySim
     * @param savedFile
     */
    public static void main(Configuration simulationConfig, Map<Slider, ProbConstant> myNewProbCatch, Simulation mySim, String savedFile) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement;
            double prob=0;
            double sharkTime=0;
            double dayswithoutFood=0;
            ArrayList<Double> constants= new ArrayList<>();
            for(ProbConstant c:myNewProbCatch.values()){
                constants.add(c.getProbCatch());
            }
            prob=constants.get(0);
            System.out.println(prob);
            sharkTime=constants.get(1);
            System.out.println(sharkTime);
            dayswithoutFood=constants.get(2);
            System.out.println(dayswithoutFood);

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
            mainRootElement.appendChild(getCompany(doc,simulationConfig.getTitle(), simulationConfig, prob,sharkTime,dayswithoutFood));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(new File("resources/"+savedFile+".xml"));
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
     * @param shark
     * @param days
     * @return
     */
    private static Node getCompany(Document doc, String title, Configuration simulation, double prob,double shark, double days) {
        Element company = doc.createElement("gridlayout");
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(0), title));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(1), String.valueOf(simulation.getMaxStates())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(2), String.valueOf(simulation.getRows())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(3), String.valueOf(simulation.getColumns())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(4), String.valueOf(simulation.getLeft())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(5), String.valueOf(simulation.getRight())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(6), String.valueOf(simulation.getTop())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(7), String.valueOf(simulation.getBottom())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(8), String.valueOf(simulation.getNeighbours())));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(9), simulation.getType1()));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(10),simulation.getType2()));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(11), simulation.getType3()));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(12), String.valueOf(prob)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(13), String.valueOf(shark)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(14), simulation.getProbCatchLabel().get(0)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(15), simulation.getProbCatchLabel().get(1)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(16), simulation.getProbCatchLabel().get(2)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(17), String.valueOf(simulation.getMaxProb().get(0))));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(18), String.valueOf(simulation.getMaxProb().get(1))));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(19), String.valueOf(days)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(20), String.valueOf(simulation.getMaxProb().get(2))));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(21), simulation.getNeighPatterninString()));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(22), simulation.getShape()));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(23), conc));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(24), "Given"));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(25), simulation.getColors().get(0)));
        company.appendChild(getCompanyElements(doc, company, Prey.DATA_FIELDS.get(26), simulation.getMyboundary()));
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