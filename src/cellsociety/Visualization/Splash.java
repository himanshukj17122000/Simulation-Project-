package cellsociety.Visualization;

import cellsociety.Configuration.Configuration;
import cellsociety.Layout;
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
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static cellsociety.Main.TITLE;

public class Splash {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static final Paint SCREEN_BACKGROUND = Color.web("0e1e38");
    private static final String BUTTON_STYLE_COLOR = "#bbd0ef";
    private static final int BUTTON_FONT_SIZE = 16;
    private static final Paint BUTTON_FONT_COLOR = Color.BLACK;
    private static final String BUTTON_UPLOAD = "Choose Simulation";
    private static final int TITLE_FONT_SIZE = 20;
    private static final String TITLE_FONT_WEIGHT = "bold";
    private static final String TITLE_FILL = "white";
    private static final String ERROR_MESSAGE= "No file chosen";

    private Scene mySplashScene;
    //private Configuration mySimulationConfig;

    // Constructor for the Splash class
    public Splash(Stage primaryStage) {
        mySplashScene = buildSplashScene(primaryStage);
    }

    // Getter method for the splash scene to be called in Main
    public Scene getSplashScene() {
        return mySplashScene;
    }

    private Scene buildSplashScene(Stage primaryStage) {
        Text simTitle = new Text(TITLE);
        simTitle.setStyle("-fx-font-size:" + TITLE_FONT_SIZE + "; -fx-font-weight: " + TITLE_FONT_WEIGHT + "; " +
                "-fx-fill:" + TITLE_FILL);
        simTitle.setTextAlignment(TextAlignment.CENTER);
        Layout layout = new Layout();
        Button buttonUpload = layout.createButton(BUTTON_UPLOAD, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR,
                BUTTON_FONT_SIZE);
        buttonUpload.setLayoutX(SCREEN_WIDTH/2);
        uploadSim(buttonUpload, primaryStage);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Background splashBackground = new Background(new BackgroundFill(SCREEN_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
        root.setBackground(splashBackground);
        root.getChildren().addAll(simTitle, buttonUpload);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage) {
        buttonUpload.setOnAction(e -> {
            try {
                DialogBox popup = new DialogBox();
                popup.start(primaryStage);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                new Alert(Alert.AlertType.ERROR, ERROR_MESSAGE).showAndWait();
            }
        });
    }
}

