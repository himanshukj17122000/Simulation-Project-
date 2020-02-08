package cellsociety.Visualization;

import cellsociety.Configuration.Configuration;
import cellsociety.GridEntry;
import cellsociety.Layout;
import cellsociety.ProbConstant;
import cellsociety.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Visualization {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");
    private static final double GRID_WIDTH = 750.0;
    private static final double GRID_HEIGHT = 750.0;
    private static final int FRAMES_PER_SECOND = 60;
    private static final double DEFAULT_SPEED = 500;
    private static final String BUTTON_STYLE_COLOR = "#bbd0ef";
    private static final Paint BUTTON_FONT_COLOR = Color.BLACK;
    private static final int BUTTON_FONT_SIZE = 16;
    private static final double MAX_SPEED = 1000;
    private static final double MIN_SPEED = 0;
    private static final String ButtonHome = "Back to Main";
    private static final String ButtonPause = "Pause Simulation";
    private static final String ButtonStep = "Next Step";
    private static final String ButtonResume = "Resume Simulation";
    private static final String ButtonStop = "Stop Simulation";
    private static final String ButtonRestart = "Restart Simulation";
    private static final String ButtonUpload = "Upload New Simulation";
    private static final String ErrorMessage= "No file chosen";

    private Scene myAnimationScene;
    private VBox myToolBar;
    private GridPane myGrid;
    private Simulation mySimulation;
    private Layout myLayout;
    private Configuration mySimulationConfig;
    private Timeline myTimeline;
    private Boolean isPaused;
    private HashMap<Slider, ProbConstant> myNewProbCatch;
    private PieChart stats;
    private double mySpeed;

    public Visualization(Stage primaryStage, Configuration simulationConfig) {
        myAnimationScene = buildAnimationScene(primaryStage, simulationConfig);
    }

    // Getter methods
    public Scene getAnimationScene() { return myAnimationScene; }

    public HashMap<Slider, ProbConstant> getNewProbCatch() { return myNewProbCatch; }

    private List<Double> getParameters(){
        Map<Slider, ProbConstant> inputHash = getNewProbCatch();
        Map<String, Double> map = new HashMap<>();
        List<String> stringArray = new ArrayList<>();
        List<Double> doubleArray = new ArrayList<>();
        int i = 0; //iterator
        for(ProbConstant pair: inputHash.values()){
            map.put(pair.getMyLabel(), pair.getMyProbCatch());
            stringArray.add(i, pair.getMyLabel());
            i++;
        }
        if(stringArray.size() > 0){
            Collections.sort(stringArray);

            for(i = 0; i < stringArray.size(); i++){
                doubleArray.add(i, map.get(stringArray.get(i)));
            }
        }

        return doubleArray;
    }

    private Scene buildAnimationScene(Stage primaryStage, Configuration simulationConfig) {
        myGrid = initializeGrid(simulationConfig);
        mySpeed = DEFAULT_SPEED;
        setSimulationLoop();
        myLayout = new Layout();
        myToolBar = buildToolBar(primaryStage, simulationConfig);
        ScrollPane myScrollPane = myLayout.createScrollPane(myToolBar);
        VBox.setVgrow(myScrollPane, Priority.ALWAYS);
        HBox myRoot = new HBox();
        Background splashBackground = new Background(new BackgroundFill(SCREEN_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
        myRoot.setBackground(splashBackground);
        myRoot.getChildren().addAll(myScrollPane, myGrid);
        return new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    // Creating the simulation loop and timeline
    private void setSimulationLoop() {
        KeyFrame myKeyFrame = new KeyFrame(Duration.millis(mySpeed), e -> step());
        myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(myKeyFrame);
        myTimeline.play();
    }

    // Updating how the grid simulation looks
    public void step(){
       /* myGroup = */mySimulation.step(getParameters());
        drawGrid(myGrid);
        myToolBar.getChildren().remove(stats);
        stats = myLayout.createChart(mySimulation);
        myToolBar.getChildren().add(stats);
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
        List<List<GridEntry>> cellStates = mySimulation.getSimulationGrid();
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
        myToolBar = new VBox(20);
        implementButtons(primaryStage, myToolBar);
        implementSlider(simulationConfig, myToolBar);
        stats = myLayout.createChart(mySimulation);
        myToolBar.getChildren().addAll(stats);
        myToolBar.setPadding(new Insets(50));
        return myToolBar;
    }

    private void implementButtons(Stage primaryStage, VBox toolBar) {
        Button buttonHome = myLayout.createButton(ButtonHome, "lightgray", BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getSplashScene()));
        Button buttonPause = myLayout.createButton(ButtonPause, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        isPaused = false;
        Button buttonStep = myLayout.createButton(ButtonStep, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonResume = myLayout.createButton(ButtonResume, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonStop = myLayout.createButton(ButtonStop, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonRestart = myLayout.createButton(ButtonRestart, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonUpload = myLayout.createButton(ButtonUpload, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        if (!isPaused) {
            pauseSim(buttonPause);
        }
        if (isPaused) {
            stepSim(buttonStep);
            resumeSim(buttonResume);
        }
        stopSim(buttonStop);
        restartSim(buttonRestart);
        uploadSim(buttonUpload, primaryStage);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonStep, buttonResume, buttonStop, buttonUpload);
    }

    private void updateProbCatch(Slider slider) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            myNewProbCatch.put(slider,
                    new ProbConstant(myNewProbCatch.get(slider).getMyLabel(), (double) newValue));
            myNewProbCatch.get(slider).setMyProbCatch((double) newValue);
        });
    }

    private void updateSpeed(Slider slider) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> mySpeed = (double) newValue);
    }

    private void implementSlider(Configuration simulationConfig, VBox toolBar) {
        myNewProbCatch = new HashMap<>();
        ArrayList<String> probCatchLabel = simulationConfig.getProbCatchLabel();
        ArrayList<Double> maxProb = simulationConfig.getMaxProb();
        ArrayList<Double> probCatch = simulationConfig.getProbCatch();
        if (probCatch != null) {
            for (int i = 0; i < probCatch.size(); i += 1) {
                Label setProbCatch = myLayout.createLabel("Set the " + probCatchLabel.get(i) + ":", 16, Color.WHITE);
                Slider probabilitySlider = myLayout.createSlider(probCatch.get(i), 0, 1, maxProb.get(i) / 2,
                        5, 0.05);
                ProbConstant probConstant = new ProbConstant(probCatchLabel.get(i), probCatch.get(i));
                myNewProbCatch.put(probabilitySlider, probConstant);
                updateProbCatch(probabilitySlider);
                toolBar.getChildren().addAll(setProbCatch, probabilitySlider);
            }
        }
        Label setSpeed = myLayout.createLabel ("Set the simulation speed:", 16, Color.WHITE);
        Slider mySpeedSlider = myLayout.createSlider(DEFAULT_SPEED, MIN_SPEED, MAX_SPEED, (MAX_SPEED - MIN_SPEED) / 2,
                (MAX_SPEED - MIN_SPEED) / 100, 100);
        updateSpeed(mySpeedSlider);
        toolBar.getChildren().addAll(setSpeed, mySpeedSlider);
    }

    // Next 6 methods: Creating the button functions
    private void pauseSim(Button buttonPause) {
        buttonPause.setOnAction(e -> myTimeline.pause());
        isPaused = true;
    }

    private void stepSim(Button buttonStep) {
        buttonStep.setOnAction(e -> {
            mySimulation.step(getParameters());
            drawGrid(myGrid);
        });
    }

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
                popup.start(primaryStage, mySimulationConfig);
                mySimulationConfig = popup.getSimulationConfig();
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                String errorMessage = ErrorMessage;
                new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
            }
        });
    }
}

