package cellsociety.Visualization;

import cellsociety.Configuration.*;
import cellsociety.*;
import cellsociety.Configuration.GofWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * @Author-Kyra Chan
 * This is the class for Visualization. It is in charge of creating the main animation scene and importing UI features.
 */

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
    private static final int PADDING = 50;
    private static final double MAX_SPEED = 1000;
    private static final double MIN_SPEED = 0;
    private static final int MINOR_TICK_COUNT = 5;
    private static final double BLOCK_INCREMENT = 0.05;
    private static final double BLOCK_INCREMENT_SPEED = 100;
    private static final String ButtonHome = "Back to Main";
    private static final String BUTTON_PAUSE = "Pause Simulation";
    private static final String BUTTON_STEP = "Next Step";
    private static final String BUTTON_RESUME = "Resume Simulation";
    private static final String BUTTON_STOP = "Stop Simulation";
    private static final String BUTTON_RESTART = "Restart Simulation";
    private static final String BUTTON_UPLOAD = "Upload New Simulation";
    private static final String BUTTON_CHANGE = "Change Simulation";
    private static final String BUTTON_SAVE = "Save Current Simulation";
    private static final String ErrorMessage= "No file chosen";

    private Scene myAnimationScene;
    private VBox myToolBar;
    private Simulation mySimulation;
    private Layout myLayout;
    private Timeline myTimeline;
    private Boolean myIsPaused;
    private HashMap<Slider, ProbConstant> myNewProbCatch;
    private PieChart myStats;
    private double mySpeed;
    private Group myGroup;

    /*
     Constructor for the Visualization class
     */
    public Visualization(Stage primaryStage, Configuration simulationConfig) {
        myAnimationScene = buildAnimationScene(primaryStage, simulationConfig);
    }

    /*
     Getter method for the animation scene to be called in Splash
     */
    public Scene getAnimationScene() { return myAnimationScene; }

    /*
     Getter method for the probability constants to update in other classes based on sliders
     */
    public HashMap<Slider, ProbConstant> getNewProbCatch() { return myNewProbCatch; }

    private List<Double> getParameters(){
        Map<Slider, ProbConstant> inputHash = getNewProbCatch();
        Map<String, Double> map = new HashMap<>();
        List<String> stringArray = new ArrayList<>();
        List<Double> doubleArray = new ArrayList<>();
        int i = 0; //iterator
        for(ProbConstant pair: inputHash.values()){
            map.put(pair.getLabel(), pair.getProbCatch());
            stringArray.add(i, pair.getLabel());
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
        initializeGrid(simulationConfig);
        mySpeed = DEFAULT_SPEED;
        setSimulationLoop();
        myLayout = new Layout();
        myToolBar = buildToolBar(primaryStage, simulationConfig);
        ScrollPane myScrollPane = myLayout.createScrollPane(myToolBar);
        VBox.setVgrow(myScrollPane, Priority.ALWAYS);
        HBox myRoot = new HBox();
        Background splashBackground = new Background(new BackgroundFill(SCREEN_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
        myRoot.setBackground(splashBackground);
        myRoot.getChildren().addAll(myScrollPane, myGroup);
        return new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private void setSimulationLoop() {
        KeyFrame myKeyFrame = new KeyFrame(Duration.millis(mySpeed), e -> step());
        myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(myKeyFrame);
        myTimeline.play();
    }

    /*
     Updating how the grid simulation looks
     */
    public void step(){
        myGroup = mySimulation.step(getParameters());
        if (myStats != null) {
            myToolBar.getChildren().remove(myStats);
            myStats = myLayout.createChart(mySimulation);
            myToolBar.getChildren().add(myStats);
        }
    }

    private void initializeSimulation(Configuration simulationConfig){
        myGroup = new Group();
        mySimulation = new Simulation(myGroup, simulationConfig, GRID_WIDTH, GRID_HEIGHT);
    }

    private void initializeGrid(Configuration simulationConfig) {
        initializeSimulation(simulationConfig);
    }

    private VBox buildToolBar(Stage primaryStage, Configuration simulationConfig) {
        myToolBar = new VBox(20);
        implementButtons(primaryStage, myToolBar, simulationConfig);
        implementSlider(simulationConfig, myToolBar);
        if (! simulationConfig.getTitle().equals("Segregation") & ! simulationConfig.getTitle().equals("Rps")) {
            myStats = myLayout.createChart(mySimulation);
            myToolBar.getChildren().addAll(myStats);
        }
        myToolBar.setPadding(new Insets(PADDING));
        return myToolBar;
    }

    private void implementButtons(Stage primaryStage, VBox toolBar, Configuration simulationConfig) {
        Button buttonHome = myLayout.createButton(ButtonHome, "lightgray", BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getSplashScene()));
        Button buttonPause = myLayout.createButton(BUTTON_PAUSE, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        myIsPaused = false;
        Button buttonStep = myLayout.createButton(BUTTON_STEP, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonResume = myLayout.createButton(BUTTON_RESUME, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonStop = myLayout.createButton(BUTTON_STOP, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonRestart = myLayout.createButton(BUTTON_RESTART, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonChange = myLayout.createButton(BUTTON_CHANGE, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        Button buttonUpload = myLayout.createButton(BUTTON_UPLOAD, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        TextField fileName = new TextField();
        fileName.setPromptText("Enter a name for the saved file:");
        //String savedFile = fileName.getText();
        Button buttonSave = myLayout.createButton(BUTTON_SAVE, BUTTON_STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        if (!myIsPaused) {
            pauseSim(buttonPause);
        }
        if (myIsPaused) {
            stepSim(buttonStep);
            resumeSim(buttonResume);
        }
        stopSim(buttonStop);
        restartSim(buttonRestart);
        changeSim(buttonChange, primaryStage);
        uploadSim(buttonUpload);
        saveSim(buttonSave, simulationConfig, fileName);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonStep, buttonResume, buttonStop, buttonChange, buttonUpload);
        if (simulationConfig.getTitle() != "Percolation") {
            toolBar.getChildren().addAll(fileName, buttonSave);
        }
    }

    private void updateProbCatch(Slider slider) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            myNewProbCatch.put(slider,
                    new ProbConstant(myNewProbCatch.get(slider).getLabel(), (double) newValue));
            myNewProbCatch.get(slider).setProbCatch((double) newValue);
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
                Slider probabilitySlider = myLayout.createSlider(probCatch.get(i), 0, maxProb.get(i),
                        maxProb.get(i) / 2, MINOR_TICK_COUNT, BLOCK_INCREMENT);
                ProbConstant probConstant = new ProbConstant(probCatchLabel.get(i), probCatch.get(i));
                myNewProbCatch.put(probabilitySlider, probConstant);
                updateProbCatch(probabilitySlider);
                toolBar.getChildren().addAll(setProbCatch, probabilitySlider);
            }
        }
        Label setSpeed = myLayout.createLabel ("Set the simulation speed:", 16, Color.WHITE);
        Slider mySpeedSlider = myLayout.createSlider(DEFAULT_SPEED, MIN_SPEED, MAX_SPEED, (MAX_SPEED - MIN_SPEED) / 2,
                (MAX_SPEED - MIN_SPEED) / 100, BLOCK_INCREMENT_SPEED);
        updateSpeed(mySpeedSlider);
        toolBar.getChildren().addAll(setSpeed, mySpeedSlider);
    }

    private void pauseSim(Button buttonPause) {
        buttonPause.setOnAction(e -> myTimeline.pause());
        myIsPaused = true;
    }

    private void stepSim(Button buttonStep) {
        buttonStep.setOnAction(e -> {
            myGroup = mySimulation.step(getParameters());
        });
    }

    private void resumeSim(Button buttonResume) {
        buttonResume.setOnAction(e -> myTimeline.play());
        myIsPaused = false;
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

    private void changeSim(Button buttonChange, Stage primaryStage) {
        buttonChange.setOnAction(e -> {
            try {
                DialogBox popup = new DialogBox();
                popup.start(primaryStage);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                new Alert(Alert.AlertType.ERROR, ErrorMessage).showAndWait();
            }
        });
    }

    private void uploadSim(Button buttonUpload) {
        buttonUpload.setOnAction(e -> {
            Stage newScreen = new Stage();
            Main newSimulation = new Main();
            newSimulation.start(newScreen);
        });
    }

    private void saveSim(Button buttonSave, Configuration simulationConfig, TextField fileName) {
        buttonSave.setOnAction(e -> {
            if(simulationConfig.getTitle().equals("Fire")){
                FireWriter fireWriter = new FireWriter();
                if ((fileName.getText() != null && !fileName.getText().isEmpty())) {
                    fireWriter.main(simulationConfig, myNewProbCatch, mySimulation, fileName.getText());
                }
                else {
                    fireWriter.main(simulationConfig, myNewProbCatch, mySimulation, "new"+simulationConfig.getTitle());
                }
            } else if(simulationConfig.getTitle().equals("Prey")){
                PreyWriter preyWriter = new PreyWriter();
                if ((fileName.getText() != null && !fileName.getText().isEmpty())) {
                    preyWriter.main(simulationConfig, myNewProbCatch, mySimulation, fileName.getText());
                }
                else {
                    preyWriter.main(simulationConfig, myNewProbCatch, mySimulation, "new"+simulationConfig.getTitle());
                }
            } else if(simulationConfig.getTitle().equals("Game of Life")) {
            GofWriter gofWriter = new GofWriter();
            if ((fileName.getText() != null && !fileName.getText().isEmpty())) {
                gofWriter.main(simulationConfig, myNewProbCatch, mySimulation, fileName.getText());
            }
            else {
                gofWriter.main(simulationConfig, myNewProbCatch, mySimulation, "new"+simulationConfig.getTitle());
            }} else if(simulationConfig.getTitle().equals("Segregation")){
                SegregationWriter segWriter = new SegregationWriter();
                if ((fileName.getText() != null && !fileName.getText().isEmpty())) {
                    segWriter.main(simulationConfig, myNewProbCatch, mySimulation, fileName.getText());
                }
                else {
                    segWriter.main(simulationConfig, myNewProbCatch, mySimulation, "new"+simulationConfig.getTitle());
                }
            }else if(simulationConfig.getTitle().equals("Rps")){
                RpsWriter rpsWriter = new RpsWriter();
                if ((fileName.getText() != null && !fileName.getText().isEmpty())) {
                    rpsWriter.main(simulationConfig, myNewProbCatch, mySimulation, fileName.getText());
                }
                else {
                    rpsWriter.main(simulationConfig, myNewProbCatch, mySimulation, "new"+simulationConfig.getTitle());
                }
            }
        });
    }
}