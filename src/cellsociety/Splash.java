package cellsociety;

import cellsociety.Configuration.Configuration;
import cellsociety.Configuration.FileInputException;
import cellsociety.Configuration.Reader;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import static cellsociety.Main.TITLE;

public class Splash {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("0e1e38");
    public static final String BUTTON_STYLE_COLOR = "#3197bc";
    public static final int BUTTON_FONT_SIZE = 16;

    private Scene mySplashScene;
    private Configuration mySimulationConfig;

    public Splash(Stage primaryStage) {
        mySplashScene = buildSplashScene(primaryStage);
    }

    public Scene getMySplashScene() {
        return mySplashScene;
    }
    public Configuration getSimulationConfig() { return mySimulationConfig; }

    private Scene buildSplashScene(Stage primaryStage) {
        Text simTitle = new Text(TITLE);
        simTitle.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-fill: white");
        simTitle.setTextAlignment(TextAlignment.CENTER);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        buttonUpload.setLayoutX(550);
        uploadSim(buttonUpload, primaryStage);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Background splashBackground = new Background(new BackgroundFill(SCREEN_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
        root.setBackground(splashBackground);
        root.getChildren().addAll(simTitle, buttonUpload);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        return button;
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage) {
        buttonUpload.setOnAction(e -> {
            try {
                start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static final String DATA_FILE_EXTENSION = "*.xml";
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    public void start (Stage primaryStage) throws Exception {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        try {
            if(dataFile.getName().equals("fire.xml")){
                new Reader("type").getFire(dataFile);
                mySimulationConfig = Configuration.getFireClass();
            }
            else if(dataFile.getName().equals("gameOfLife.xml")){
                new Reader("type").getGame(dataFile);
                mySimulationConfig = Configuration.getGameClass();
            }
            else if(dataFile.getName().equals("percolation.xml")){
                new Reader("type").getPercolation(dataFile);
                mySimulationConfig = Configuration.getPerClass();
            }
            else if(dataFile.getName().equals("prey.xml")){
                new Reader("type").getPrey(dataFile);
                mySimulationConfig = Configuration.getPreyClass();
            }
            else if(dataFile.getName().equals("segregation.xml")){
                new Reader("type").getSegregation(dataFile);
                mySimulationConfig = Configuration.getSegClass();
            }
            Visualization animation = new Visualization(primaryStage, mySimulationConfig);
            primaryStage.setScene(animation.getMyAnimationScene());
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
}

