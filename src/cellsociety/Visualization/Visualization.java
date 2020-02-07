package cellsociety.Visualization;

import cellsociety.Configuration.Configuration;
import cellsociety.GridEntry;
import cellsociety.Layout;
import cellsociety.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

public class Visualization {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");
    private static final double GRID_WIDTH = 750.0;
    private static final double GRID_HEIGHT = 750.0;
    private static final int FRAMES_PER_SECOND = 60;
    private static final double DEFAULT_SPEED = 500;

    private Scene myAnimationScene;
    private HBox myRoot;
    private GridPane myGrid;
    private Simulation mySimulation;
    private Layout myLayout;

    public Visualization(Stage primaryStage, Configuration simulationConfig) {
        myAnimationScene = buildAnimationScene(primaryStage, simulationConfig);
    }

    // Getter methods
    public Scene getAnimationScene() { return myAnimationScene; }
    //public Configuration getSimulationConfig() { return mySimulationConfig; }
    private Simulation getSimulation(){ return mySimulation; }
    public HBox getRoot() { return myRoot; }
    public void setRoot(HBox root) { myRoot = root; }

    private Scene buildAnimationScene(Stage primaryStage, Configuration simulationConfig) {
        myGrid = initializeGrid(simulationConfig);
        myLayout.setSpeed(DEFAULT_SPEED);
        setSimulationLoop();
        myLayout = new Layout();
        VBox toolBar = buildToolBar(primaryStage, simulationConfig);
        myRoot = new HBox();
        Background splashBackground = new Background(new BackgroundFill(SCREEN_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
        myRoot.setBackground(splashBackground);
        myRoot.getChildren().addAll(toolBar, myGrid);
        return new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    // Creating the simulation loop and timeline
    private void setSimulationLoop() {
        KeyFrame myKeyFrame = new KeyFrame(Duration.millis(myLayout.getSpeed()), e -> step());
        myLayout.setTimeline(new Timeline());
        myLayout.getTimeline().setCycleCount(Timeline.INDEFINITE);
        myLayout.getTimeline().getKeyFrames().add(myKeyFrame);
        myLayout.getTimeline().play();
    }

    // Updating how the grid simulation looks
    public void step(){
        mySimulation.step();
        drawGrid(myGrid);
        if (! myLayout.getIsPaused()) myLayout.pauseSim(myLayout.getButtonPause());
        if (myLayout.getIsPaused()) {
            stepSim(myLayout.getButtonStep());
            myLayout.resumeSim(myLayout.getButtonResume());
        }
    }

    private void initializeSimulation(List<List<GridEntry>> cellArray){
        mySimulation = new Simulation(cellArray);
    }

    private GridPane initializeGrid(Configuration simulationConfig) {
        List<List<GridEntry>> cellStates = simulationConfig.makeCellGrid();
        initializeSimulation(cellStates);
        GridPane initializedGrid = new GridPane();
        return drawGrid(initializedGrid);
    }

    // Redrawing the grid after every time step, adding each cell to the grid
    public GridPane drawGrid(GridPane grid) {
        List<List<GridEntry>> cellStates = getSimulation().getSimulationGrid();
        grid.getChildren().clear();
        grid.setPrefSize(GRID_WIDTH,GRID_HEIGHT);
        grid.setStyle("-fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 25; -fx-border-color: black;");
        for (int row = 0; row < cellStates.size(); row += 1) {
            for (int col = 0; col < cellStates.get(row).size(); col += 1) {
                Rectangle cell = cellStates.get(row).get(col).getCell().getRectangle();
                cell.setWidth(GRID_WIDTH/cellStates.get(row).size());
                cell.setHeight(GRID_HEIGHT/cellStates.size());
                grid.add(cell, row, col);
            }
        }
        return grid;
    }

    private VBox buildToolBar(Stage primaryStage, Configuration simulationConfig) {
        VBox toolBar = new VBox(20);
        myLayout.implementButtons(primaryStage, toolBar);
        myLayout.implementSlider(simulationConfig, toolBar);
        toolBar.setPadding(new Insets(50));
        return toolBar;
    }
    /*
    private void implementButtons(Stage primaryStage, VBox toolBar) {
        Button buttonHome = createButton("Back to Main", "lightgray", BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getSplashScene()));
        buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        isPaused = false;
        if (!isPaused) {
            pauseSim(buttonPause);
        }
        buttonStep = createButton("Next Step", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        buttonResume = createButton("Resume Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        if (isPaused) {
            stepSim(buttonStep);
            resumeSim(buttonResume);
        }
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopSim(buttonStop);
        Button buttonRestart = createButton("Restart Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        restartSim(buttonRestart);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonStep, buttonResume, buttonStop, buttonUpload);
    }

    private Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        button.setPrefWidth(180);
        return button;
    }

    private void updateProbCatch(Slider slider, Configuration simulationConfig) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> simulationConfig.setProbCatch((double) newValue));
    }

    private void updateSpeed(Slider slider) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> mySpeed = (double) newValue);
    }

    private void implementSlider(Configuration simulationConfig, VBox toolBar) {
        String probCatchLabel = simulationConfig.getProbCatchLabel();
        if (probCatchLabel != null) {
            Label setProbCatch = myLayout.createLabel("Set the " + probCatchLabel + ":", 16, Color.WHITE);
            Slider probabilitySlider = createSlider(simulationConfig.getProbCatch(), 0, 1, 0.5,
                    5, 0.05);
            updateProbCatch(probabilitySlider, simulationConfig);
            toolBar.getChildren().addAll(setProbCatch, probabilitySlider);
        }
        Label setSpeed = myLayout.createLabel ("Set the simulation speed:", 16, Color.WHITE);
        Slider mySpeedSlider = createSlider(DEFAULT_SPEED, MIN_SPEED, MAX_SPEED, (MAX_SPEED - MIN_SPEED) / 2,
                (MAX_SPEED - MIN_SPEED) / 100, 100);
        updateSpeed(mySpeedSlider);
        toolBar.getChildren().addAll(setSpeed, mySpeedSlider);
    }

    private Label createLabel(String text, int fontSize, Color textFill) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize);
        label.setTextFill(textFill);
        return label;
    }

    private Slider createSlider(double defaultValue, double min, double max, double majorTickUnit,
                                double minorTickCount, double blockIncrement) {
        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(defaultValue);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(majorTickUnit);
        slider.setMinorTickCount((int) minorTickCount);
        slider.setBlockIncrement(blockIncrement);
        slider.setStyle("-fx-tick-label-fill: white");
        return slider;
    }

    // Next 6 methods: Creating the button functions
    private void pauseSim(Button buttonPause) {
        buttonPause.setOnAction(e -> myTimeline.pause());
        isPaused = true;
    }
    */
    private void stepSim(Button buttonStep) {
        buttonStep.setOnAction(e -> {
            mySimulation.step();
            drawGrid(myGrid);
        });
    }
/*
    private void resumeSim(Button buttonResume) {
        buttonResume.setOnAction(e -> myTimeline.play());
        isPaused = false;
    }

    private void restartSim(Button buttonRestart) {
        buttonRestart.setOnAction(e -> {
            myTimeline.pause();
            myTimeline = new Timeline();
        });
    }

    private void stopSim(Button buttonStop) {
        buttonStop.setOnAction(e -> myTimeline.stop());
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage) {
        buttonUpload.setOnAction(e -> {
            try {
                DialogBox popup = new DialogBox();
                popup.start(primaryStage, this.getSimulationConfig());
                mySimulationConfig = popup.getSimulationConfig();
            } catch (NullPointerException ex) {
                String errorMessage = "No file chosen";
                new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
            }
        });
    }
     */
}

