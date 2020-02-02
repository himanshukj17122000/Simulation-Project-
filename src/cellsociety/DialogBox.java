package cellsociety;

import cellsociety.Configuration.Configuration;
import cellsociety.Configuration.FileInputException;
import cellsociety.Configuration.Reader;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class DialogBox {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    private Configuration mySimulationConfig;

    public void start(Stage primaryStage, Configuration simConfig) throws Exception {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        try {
            switch (dataFile.getName()) {
                case "fire.xml":
                    new Reader("type").getFire(dataFile);
                    simConfig = Configuration.getFireClass();
                    break;
                case "gameOfLife.xml":
                    new Reader("type").getGame(dataFile);
                    simConfig = Configuration.getGameClass();
                    break;
                case "percolation.xml":
                    new Reader("type").getPercolation(dataFile);
                    simConfig = Configuration.getPerClass();
                    break;
                case "prey.xml":
                    new Reader("type").getPrey(dataFile);
                    simConfig = Configuration.getPreyClass();
                    break;
                case "segregation.xml":
                    new Reader("type").getSegregation(dataFile);
                    simConfig = Configuration.getSegClass();
                    break;
            }
            Visualization animation = new Visualization(primaryStage, simConfig);
            primaryStage.setScene(animation.getMyAnimationScene());
            mySimulationConfig = simConfig;
        }
        catch (FileInputException e) {
            // handle error of unexpected file format
            showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
        // nothing selected, so quit the application
        if (dataFile == null) Platform.exit();
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
    public Configuration getMySimulationConfig() { return mySimulationConfig; }
}
