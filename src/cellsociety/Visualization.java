package cellsociety;

import cellsociety.Configuration.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputFilter;
import java.util.List;
import java.util.Map;

import static cellsociety.Main.TITLE;

public class Visualization {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Paint SCREEN_BACKGROUND = Color.web("1f2e50");
    public static final double GRID_WIDTH = 600.0;
    public static final double GRID_HEIGHT = 600.0;
    public static final String BUTTON_STYLE_COLOR = "#3197bc";
    public static final int BUTTON_FONT_SIZE = 16;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myAnimationScene;
    private Configuration myNewSimulationConfig;
    private Simulation mySimulation;
    private Timeline timeline;

    public Visualization(Stage primaryStage, Configuration simulationConfig) {
        myAnimationScene = buildAnimationScene(primaryStage, simulationConfig);
    }

    public Scene getMyAnimationScene() {
        return myAnimationScene;
    }

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

    private VBox buildToolBar(Stage primaryStage, Configuration simulationConfig) {
        VBox toolBar = new VBox(20);
        Slider probabilitySlider = createSlider(simulationConfig.getProbCatch());
        Button buttonHome = createButton("Back to Main", "lightgray", BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getMySplashScene()));
        Button buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        pauseGame(buttonPause);
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopGame(buttonStop);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonStop, probabilitySlider, buttonUpload);
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
        buttonPause.setOnAction(e -> timeline.pause());
    }

    private void stopGame(Button buttonStop) {
        buttonStop.setOnAction(e -> timeline.stop());
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage) {
        buttonUpload.setOnAction(e -> {
            //if (timeline.getStatus() != Animation.Status.STOPPED) timeline.stop();
            try {
                start(primaryStage, timeline);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setSimulationLoop(GridPane grid) {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(grid));
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    public static final String DATA_FILE_EXTENSION = "*.xml";
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    public void start (Stage primaryStage, Timeline timeline) throws Exception {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        try {
            if(dataFile.getName().equals("fire.xml")){
                new Reader("type").getFire(dataFile);
                myNewSimulationConfig = Configuration.getFireClass();
            }
            else if(dataFile.getName().equals("gameOfLife.xml")){
                new Reader("type").getGame(dataFile);
                myNewSimulationConfig = Configuration.getGameClass();
            }
            else if(dataFile.getName().equals("percolation.xml")){
                //simulationConfig = new Percolation();
                new Reader("type").getPercolation(dataFile);
                myNewSimulationConfig = Configuration.getPerClass();
            }
            else if(dataFile.getName().equals("prey.xml")){
                //simulationConfig = new Prey();
                new Reader("type").getPrey(dataFile);
                myNewSimulationConfig = Configuration.getPreyClass();
            }
            else if(dataFile.getName().equals("segregation.xml")){
                //simulationConfig = new Segregation();
                new Reader("type").getSegregation(dataFile);
                myNewSimulationConfig = Configuration.getSegClass();
            }
            Visualization newAnimation = new Visualization(primaryStage, myNewSimulationConfig);
            primaryStage.setScene(newAnimation.getMyAnimationScene());
        }
        catch (FileInputException e) {
            // handle error of unexpected file format
            showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
        // nothing selected, so quit the application
        if (dataFile == null) Platform.exit();
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    // set some sensible defaults when the FileChooser is created
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
}

