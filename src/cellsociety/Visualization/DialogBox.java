package cellsociety.Visualization;

import cellsociety.Configuration.*;
import cellsociety.Visualization.Visualization;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:Himanshu Jain 
 */

public class DialogBox {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    private static final String FIRE_FILE = "Fire";
    private static final String GAME_FILE = "Game of Life";
    private static final String PERC_FILE = "Percolation";
    private static final String PREY_FILE = "Prey";
    private static final String SEG_FILE = "Segregation";
    private static final String TYPE = "type";
    private static String title;
    private static Map<String,String> result= new HashMap<>();
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    private Configuration mySimulationConfig;

    public void start(Stage primaryStage, Configuration simConfig) throws NullPointerException {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(dataFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("gridlayout");
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                title= getTagValue("title", eElement);
            }
        try {
            switch (title) {
                case FIRE_FILE:
                    result.clear();
                    result= new Reader(TYPE).getSimulation(dataFile);
                    simConfig = new Fire(result);
                    break;
                case GAME_FILE:
                    result.clear();
                    result= new Reader(TYPE).getSimulation(dataFile);
                    simConfig = new Game(result);
                    break;
                case PERC_FILE:
                    result.clear();
                    result= new Reader(TYPE).getSimulation(dataFile);
                    simConfig = new Percolation(result);
                    break;
                case PREY_FILE:
                    result.clear();
                    result= new Reader(TYPE).getPrey(dataFile);
                    simConfig = new Prey(result);
                    break;
                case SEG_FILE:
                    result.clear();
                    result= new Reader(TYPE).getSimulation(dataFile);
                    simConfig = new Segregation(result);
                    break;
            }
            Visualization animation = new Visualization(primaryStage, simConfig);
            primaryStage.setScene(animation.getAnimationScene());
            mySimulationConfig = simConfig;
        }
        catch (FileInputException e) {
            // handle error of unexpected file format
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