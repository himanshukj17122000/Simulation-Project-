package cellsociety;

import cellsociety.Configuration.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.List;

public class Visualization {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");
    public static final double GRID_WIDTH = 600.0;
    public static final double GRID_HEIGHT = 600.0;
    public static final String BUTTON_STYLE_COLOR = "#3197bc";
    public static final int BUTTON_FONT_SIZE = 16;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 4000 / FRAMES_PER_SECOND;

    private Scene myAnimationScene;
    private Configuration mySimulationConfig;
    private Simulation mySimulation;
    private Timeline myTimeline;

    public Visualization(Stage primaryStage, Configuration simulationConfig) {
        myAnimationScene = buildAnimationScene(primaryStage, simulationConfig);
    }

    public Scene getMyAnimationScene() {
        return myAnimationScene;
    }
    public Configuration getMySimulationConfig() { return mySimulationConfig; }

    private Scene buildAnimationScene(Stage primaryStage, Configuration simulationConfig) {

        GridPane grid = initializeGrid(simulationConfig);
        setSimulationLoop(grid);
        VBox toolBar = buildToolBar(primaryStage, simulationConfig);
        HBox root = new HBox();
        Background splashBackground = new Background(new BackgroundFill(SCREEN_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
        root.setBackground(splashBackground);
        root.getChildren().addAll(toolBar, grid);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private void initializeSimulation(List<List<GridEntry>> cellArray){
        mySimulation = new Simulation(cellArray);
    }

    private Simulation getMySimulation(){
        return mySimulation;
    }

    private GridPane initializeGrid(Configuration simulationConfig) {
        List<List<GridEntry>> cellStates = simulationConfig.makeCellGrid();
        initializeSimulation(cellStates);
        GridPane initializedGrid = new GridPane();
        return drawGrid(initializedGrid);
    }

    public void step(GridPane grid){
        mySimulation.step();
        drawGrid(grid);
    }

    public GridPane drawGrid(GridPane grid) {
        List<List<GridEntry>> cellStates = getMySimulation().getSimulationGrid();
        grid.getChildren().clear();
        grid.setPrefSize(GRID_WIDTH,GRID_HEIGHT);
        grid.setStyle("-fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 25; -fx-border-color: black;");
        for (int row = 0; row < cellStates.size(); row += 1) {
            for (int col = 0; col < cellStates.get(row).size(); col += 1) {
                Cell cell = cellStates.get(row).get(col).getCell();
                grid.add(cell.getRectangle(), row, col);
            }
        }
        return grid;
    }

    private VBox buildToolBar(Stage primaryStage, Configuration simulationConfig) {
        VBox toolBar = new VBox(20);
        Button buttonHome = createButton("Back to Main", "lightgray", BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getMySplashScene()));
        Button buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        pauseGame(buttonPause);
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopGame(buttonStop);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonStop, buttonUpload);
        String probCatchLabel = simulationConfig.getProbCatchLabel();
        if (probCatchLabel != null) {
            Label setProbCatch = new Label("Set the" + probCatchLabel);
            Slider probabilitySlider = createSlider(simulationConfig.getProbCatch());
            toolBar.getChildren().addAll(setProbCatch, probabilitySlider);
        }
        toolBar.setPadding(new Insets(50));
        return toolBar;
    }

    private Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        return button;
    }

    private Slider createSlider(double defaultValue) {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(defaultValue);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(0.5);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(0.05);
        slider.setStyle("-fx-tick-label-fill: white");
        return slider;
    }

    private void pauseGame(Button buttonPause) {
        buttonPause.setOnAction(e -> myTimeline.pause());
    }

    private void stopGame(Button buttonStop) {
        buttonStop.setOnAction(e -> myTimeline.stop());
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage) {
        buttonUpload.setOnAction(e -> {
            //if (myTimeline.getStatus() != Animation.Status.STOPPED) myTimeline.stop();
            try {
                //start(primaryStage, myTimeline);
                DialogBox popup = new DialogBox();
                popup.start(primaryStage, this.getMySimulationConfig());
                mySimulationConfig = popup.getMySimulationConfig();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setSimulationLoop(GridPane grid) {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(grid));
        myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(frame);
        myTimeline.play();
    }
}

