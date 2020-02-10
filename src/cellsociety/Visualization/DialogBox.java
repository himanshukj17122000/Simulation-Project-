package cellsociety.Visualization;

import cellsociety.Configuration.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DialogBox {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public static final String FIRE_FILE = "Fire";
    public static final String GAME_FILE = "Game of Life";
    public static final String PERC_FILE = "Percolation";
    public static final String PREY_FILE = "Prey";
    public static final String SEG_FILE = "Segregation";
    public static final String RPS_FILE= "Rps";
    private static final String TYPE = "type";
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    private static final FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    private static String title;
    private static Map<String,String> result= new HashMap<>();
    private Configuration mySimulationConfig;

    public void start(Stage primaryStage) throws NullPointerException, ParserConfigurationException, IOException, SAXException {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        if(dataFile==null){
            throw new IOException();
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(dataFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("gridlayout");
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                title = getTagValue("title", eElement);
            }
            else{
                showMessage(Alert.AlertType.ERROR, "No File Chosen");
            }
            Configuration simConfig;
            result.clear();
            result= new Reader(TYPE).getSimulation(title,dataFile);
            switch (title) {
                case RPS_FILE:
                    simConfig = new Rps(result);
                    break;
                case FIRE_FILE:
                    simConfig = new Fire(result);
                    break;
                case GAME_FILE:
                    simConfig = new Game(result);
                    break;
                case PERC_FILE:
                    simConfig = new Percolation(result);
                    break;
                case PREY_FILE:
                    simConfig = new Prey(result);
                    break;
                case SEG_FILE:
                    simConfig = new Segregation(result);
                    break;
                default:
                throw new FileInputException("File Not Found");
            }
            Visualization animation = new Visualization(primaryStage, simConfig);
            primaryStage.setScene(animation.getAnimationScene());
            mySimulationConfig = simConfig;
        }
        catch (FileInputException | NullPointerException e) {
            showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    // set some sensible defaults when the FileChooser is created
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }

    // Getter method for configuration to be passed on in Visualization and Simulation
    public Configuration getSimulationConfig() { return mySimulationConfig; }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}