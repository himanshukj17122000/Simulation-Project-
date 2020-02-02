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
            if(dataFile.getName().equals("fire.xml")){
                new Reader("type").getFire(dataFile);
                simConfig = Configuration.getFireClass();
            }
            else if(dataFile.getName().equals("gameOfLife.xml")){
                new Reader("type").getGame(dataFile);
                simConfig = Configuration.getGameClass();
            }
            else if(dataFile.getName().equals("percolation.xml")){
                new Reader("type").getPercolation(dataFile);
                simConfig = Configuration.getPerClass();
            }
            else if(dataFile.getName().equals("prey.xml")){
                new Reader("type").getPrey(dataFile);
                simConfig = Configuration.getPreyClass();
            }
            else if(dataFile.getName().equals("segregation.xml")){
                new Reader("type").getSegregation(dataFile);
                simConfig = Configuration.getSegClass();
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

    public Configuration getMySimulationConfig() { return mySimulationConfig; }
}
