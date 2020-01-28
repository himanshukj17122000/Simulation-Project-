package cellsociety;

import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Visualization {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");

    private Scene splashScene;
    private Timeline timeline;

    public Scene buildAnimationScene(Stage primaryStage) {
        ToolBar toolBar = buildToolBar(primaryStage, timeline);
        HBox root = new HBox();
        root.getChildren().addAll(toolBar);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private ToolBar buildToolBar(Stage primaryStage, Timeline timeline) {
        ToolBar toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);
        Button buttonHome = createButton("Back to Main", null, 16);
        buttonHome.setOnAction(e -> primaryStage.setScene(splashScene));
        Button buttonPause = createButton("Pause Simulation", "#3197bc", 16);
        pauseGame(buttonPause, timeline);
        Button buttonStop = createButton("Stop Simulation", "#3197bc", 16);
        stopGame(buttonStop, timeline);
        Button buttonUpload = createButton("Upload New Simulation", "#3197bc", 16);
        uploadSim(buttonUpload, timeline);
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

    private void uploadSim(Button buttonUpload, Timeline timeline) {
        buttonUpload.setOnAction(e -> {
            timeline.stop();
        });
    }
}

