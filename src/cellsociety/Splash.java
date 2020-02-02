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
                DialogBox popup = new DialogBox();
                popup.start(primaryStage, this.getSimulationConfig());
                mySimulationConfig = popup.getMySimulationConfig();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

