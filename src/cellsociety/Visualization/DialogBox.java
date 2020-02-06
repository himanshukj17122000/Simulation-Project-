package cellsociety.Visualization;

import cellsociety.Configuration.Configuration;
import cellsociety.Configuration.FileInputException;
import cellsociety.Configuration.Reader;
import cellsociety.Visualization.Visualization;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.ObjectInputFilter;

public class DialogBox {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    private static final String FIRE_FILE = "fire.xml";
    private static final String GAME_FILE = "gameOfLife.xml";
    private static final String PERC_FILE = "percolation.xml";
    private static final String PREY_FILE = "prey.xml";
    private static final String SEG_FILE = "segregation.xml";
    private static final String TYPE = "type";
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    private Configuration mySimulationConfig;

    public void start(Stage primaryStage, Configuration simConfig) throws NullPointerException {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        try {
            switch (dataFile.getName()) {
                case FIRE_FILE:
                    new Reader(TYPE).getFire(dataFile);
                    simConfig = Configuration.getFireClass();
                    break;
                case GAME_FILE:
                    new Reader(TYPE).getGame(dataFile);
                    simConfig = Configuration.getGameClass();
                    break;
                case PERC_FILE:
                    new Reader(TYPE).getPercolation(dataFile);
                    simConfig = Configuration.getPerClass();
                    break;
                case PREY_FILE:
                    new Reader(TYPE).getPrey(dataFile);
                    simConfig = Configuration.getPreyClass();
                    break;
                case SEG_FILE:
                    new Reader(TYPE).getSegregation(dataFile);
                    simConfig = Configuration.getSegClass();
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
}
