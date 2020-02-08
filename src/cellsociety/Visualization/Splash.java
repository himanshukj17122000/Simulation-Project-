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
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("0e1e38");
    public static final String BUTTON_STYLE_COLOR = "#bbd0ef";
    public static final int BUTTON_FONT_SIZE = 16;
    private static final Paint BUTTON_FONT_COLOR = Color.BLACK;

    private Scene mySplashScene;
    private Configuration mySimulationConfig;

    public Splash(Stage primaryStage) {
        mySplashScene = buildSplashScene(primaryStage);
    }

    public Scene getSplashScene() {
        return mySplashScene;
    }

    private Scene buildSplashScene(Stage primaryStage) {
        Text simTitle = new Text(TITLE);
        simTitle.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-fill: white");
        simTitle.setTextAlignment(TextAlignment.CENTER);
        Layout layout = new Layout();
        Button buttonUpload = layout.createButton("ButtonUpload", BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR,
                BUTTON_FONT_SIZE);
        buttonUpload.setLayoutX(550);
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
                popup.start(primaryStage, mySimulationConfig);
                mySimulationConfig = popup.getSimulationConfig();
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                String errorMessage = "ErrorMessage";
                new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
            }
        });
    }
}

