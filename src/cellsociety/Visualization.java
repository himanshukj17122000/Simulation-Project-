package cellsociety;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import static cellsociety.Main.TITLE;

public class Visualization {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");
    public static final double GRID_WIDTH = 600.0;
    public static final double GRID_HEIGHT = 600.0;
    public static final String BUTTON_STYLE_COLOR = "#3197bc";
    public static final int BUTTON_FONT_SIZE = 16;

    private Scene mySplashScene;
    private Scene myAnimationScene;

    public Visualization(Stage primaryStage, Timeline timeline, int rows, int cols, List<List<Cell>> cellStates) {
        mySplashScene = buildSplashScene(primaryStage, timeline);
        myAnimationScene = buildAnimationScene(primaryStage, timeline);
    }

    public Scene getMySplashScene() {
        return mySplashScene;
    }

    public Scene getMyAnimationScene() {
        return myAnimationScene;
    }

    public GridPane drawGrid(List<List<GridEntry>> cellStates) {
        GridPane grid = new GridPane();
        grid.setPrefSize(GRID_WIDTH,GRID_HEIGHT);
        grid.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; " +
                "-fx-border-radius: 5; -fx-border-color: black;");
        for (int row = 0; row < cellStates.size(); row += 1) {
            for (int col = 0; col < cellStates.get(row).size(); col += 1) {
                Cell cell = cellStates.get(row).get(col).getCell();
                grid.add(cell.getRectangle(), row, col);
            }
        }
        return grid;
    }

    public Scene buildSplashScene(Stage primaryStage, Timeline timeline) {
        Text simTitle = new Text(TITLE);
        simTitle.setTextAlignment(TextAlignment.CENTER);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        buttonUpload.setLayoutX(550);
        uploadSim(buttonUpload, primaryStage, timeline);
        VBox root = new VBox(20);
        root.getChildren().addAll(simTitle, buttonUpload);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    public Scene buildAnimationScene(Stage primaryStage, Timeline timeline) {
        ToolBar toolBar = buildToolBar(primaryStage, timeline);
        HBox root = new HBox();
        GridPane grid = initializeGrid();
        root.getChildren().addAll(toolBar, grid);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private GridPane initializeGrid() {
        List<List<GridEntry>> cellStates = Configuration.makeCellGrid();
        return drawGrid(cellStates);
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
            if (timeline.getStatus() != Animation.Status.STOPPED) timeline.stop();
            primaryStage.setScene(myAnimationScene);
        });
    }
}

