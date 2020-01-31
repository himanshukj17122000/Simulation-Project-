package cellsociety.Configuration;
import java.io.File;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import static javafx.application.Application.launch;

public class dialogBox {
        public static final String DATA_FILE_EXTENSION = "*.xml";
        // NOTE: generally accepted behavior that the chooser remembers where user left it last
        public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

        public void start (Stage primaryStage) throws Exception {
            File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
            while (dataFile != null) {
                try {
                    if(dataFile.getName().equals("fire.xml")){
//                        Pair<String, Game> p = new Pair<>(dataFile.getName(), new XMLParser("media").getGame(dataFile));
//                        showMessage(AlertType.INFORMATION, p.getSecond().toString());
                       new Reader("type").getFire(dataFile, dataFile.getName());
                    }
                    else if(dataFile.getName().equals("gameOfLife.xml")){
                        new Reader("type").getGame(dataFile);

                    }
                    else if(dataFile.getName().equals("percolation.xml")){
                        new Reader("type").getPercolation(dataFile);

                    }
                    else if(dataFile.getName().equals("prey.xml")){
                        new Reader("type").getPrey(dataFile);

                    }
                    else if(dataFile.getName().equals("segregation.xml")){
                        new Reader("type").getSegregation(dataFile);

                    }

                }
                catch (FileInputException e) {
                    // handle error of unexpected file format
                    showMessage(AlertType.ERROR, e.getMessage());
                }
                dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
            }

            // nothing selected, so quit the application
            Platform.exit();
        }

        // display given message to user using the given type of Alert dialog box
        private void showMessage (AlertType type, String message) {
            new Alert(type, message).showAndWait();
        }

        // set some sensible defaults when the FileChooser is created
        private static FileChooser makeChooser (String extensionAccepted) {
            FileChooser result = new FileChooser();
            result.setTitle("Open Data File");
            // pick a reasonable place to start searching for files
            result.setInitialDirectory(new File(System.getProperty("user.dir")));
            result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
            return result;
        }


        /**
         * Start of the program.
         */
        public static void main (String[] args) {
            launch(args);
        }
    }


