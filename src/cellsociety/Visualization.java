package cellsociety;

import cellsociety.Configuration.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    public static final int MILLISECOND_DELAY = 15000 / FRAMES_PER_SECOND;


    private Scene myAnimationScene;
    private Configuration mySimulationConfig;
    private Simulation mySimulation;
    private Timeline myTimeline;
    private Button buttonPause;
    private Button buttonResume;
    private Boolean isPaused;

    public Visualization(Stage primaryStage, Configuration simulationConfig) {
        myAnimationScene = buildAnimationScene(primaryStage, simulationConfig);
    }

    // Getter methods
    public Scene getMyAnimationScene() { return myAnimationScene; }
    public Configuration getMySimulationConfig() { return mySimulationConfig; }
    private Simulation getMySimulation(){ return mySimulation; }

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

    // Creating the simulation loop and timeline
    private void setSimulationLoop(GridPane grid) {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(grid));
        myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(frame);
        myTimeline.play();
    }

    // Updating how the grid simulation looks
    public void step(GridPane grid){
        mySimulation.step();
        drawGrid(grid);
        if (!isPaused) pauseGame(buttonPause);
        if (isPaused) resumeGame(buttonResume);    }

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

    // Next 4 Methods: Creating the toolbar for the visualization scene
    private VBox buildToolBar(Stage primaryStage, Configuration simulationConfig) {
        VBox toolBar = new VBox(20);
        implementButtons(primaryStage, toolBar);
        implementSlider(simulationConfig, toolBar);
        toolBar.setPadding(new Insets(50));
        return toolBar;
    }

    private void implementButtons(Stage primaryStage, VBox toolBar) {
        Button buttonHome = createButton("Back to Main", "lightgray", BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getMySplashScene()));
        buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        isPaused = false;
        if (!isPaused) pauseGame(buttonPause);
        buttonResume = createButton("Resume Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        if (isPaused) resumeGame(buttonResume);
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopGame(buttonStop);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonResume, buttonStop, buttonUpload);
    }

    private Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        return button;
    }

    private void updateProbCatch(Slider slider, Configuration simulationConfig) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                simulationConfig.setProbCatch((double) newValue);
            }
        });
    }

    private void implementSlider(Configuration simulationConfig, VBox toolBar) {
        String probCatchLabel = simulationConfig.getProbCatchLabel();
        if (probCatchLabel != null) {
            Label setProbCatch = new Label("Set the " + probCatchLabel);
            setProbCatch.setStyle("-fx-font-size: 16");
            setProbCatch.setTextFill(Color.WHITE);
            Slider probabilitySlider = createSlider(simulationConfig.getProbCatch());
            updateProbCatch(probabilitySlider, simulationConfig);
            toolBar.getChildren().addAll(setProbCatch, probabilitySlider);
        }
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

    // Next 3 methods: Creating the button functions
    private void pauseGame(Button buttonPause) {
        buttonPause.setOnAction(e -> myTimeline.pause());
        isPaused = true;
        System.out.println(isPaused);
    }

    private void resumeGame(Button buttonResume) {
        buttonResume.setOnAction(e -> myTimeline.play());
        isPaused = false;
        System.out.println(isPaused);
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
}

