package cellsociety;

import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static cellsociety.Main.TITLE;

public class Visualization {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");
    public static final String BUTTON_STYLE_COLOR = "#3197bc";
    public static final int BUTTON_FONT_SIZE = 16;

    private Scene mySplashScene;
    private Scene myAnimationScene;

    public Visualization(Stage primaryStage, Timeline timeline) {
        mySplashScene = buildSplashScene(primaryStage, timeline);
        myAnimationScene = buildAnimationScene(primaryStage, timeline);
    }

    public Scene getMySplashScene() {
        return mySplashScene;
    }

    public Scene getMyAnimationScene() {
        return myAnimationScene;
    }

    public Scene buildSplashScene(Stage primaryStage, Timeline timeline) {
        Text simTitle = new Text(TITLE);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage, timeline);
        VBox root = new VBox(20);
        root.getChildren().addAll(simTitle, buttonUpload);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    public Scene buildAnimationScene(Stage primaryStage, Timeline timeline) {
        ToolBar toolBar = buildToolBar(primaryStage, timeline);
        HBox root = new HBox();
        root.getChildren().addAll(toolBar);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private ToolBar buildToolBar(Stage primaryStage, Timeline timeline) {
        ToolBar toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);
        Button buttonHome = createButton("Back to Main", null, BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(mySplashScene));
        Button buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        pauseGame(buttonPause, timeline);
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopGame(buttonStop, timeline);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage, timeline);
        toolBar.getItems().addAll(buttonHome, buttonPause, buttonStop, buttonUpload);
        return toolBar;
    }

    private Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        return button;
    }

    private void pauseGame(Button buttonPause, Timeline timeline) {
        buttonPause.setOnAction(e -> timeline.pause());
    }

    private void stopGame(Button buttonStop, Timeline timeline) {
        buttonStop.setOnAction(e -> timeline.stop());
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage, Timeline timeline) {
        buttonUpload.setOnAction(e -> {
            timeline.stop();

            primaryStage.setScene(myAnimationScene);
        });
    }
}

