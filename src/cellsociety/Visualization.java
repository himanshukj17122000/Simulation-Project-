package cellsociety;

import cellsociety.Configuration.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    //private Configuration simulationConfig;

    public Visualization(Stage primaryStage, Timeline timeline, Configuration simulationConfig) {
        mySplashScene = buildSplashScene(primaryStage, timeline);
        myAnimationScene = buildAnimationScene(primaryStage, timeline, simulationConfig);
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

    private Scene buildSplashScene(Stage primaryStage, Timeline timeline) {
        Text simTitle = new Text(TITLE);
        simTitle.setTextAlignment(TextAlignment.CENTER);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        buttonUpload.setLayoutX(550);
        uploadSim(buttonUpload, primaryStage, timeline);
        VBox root = new VBox(20);
        root.getChildren().addAll(simTitle, buttonUpload);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private Scene buildAnimationScene(Stage primaryStage, Timeline timeline, Configuration simulationConfig) {
        ToolBar toolBar = buildToolBar(primaryStage, timeline, simulationConfig);
        HBox root = new HBox();
        GridPane grid = initializeGrid(simulationConfig);
        root.getChildren().addAll(toolBar, grid);
        return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_BACKGROUND);
    }

    private GridPane initializeGrid(Configuration simulationConfig) {
        List<List<GridEntry>> cellStates = simulationConfig.makeCellGrid();
        return drawGrid(cellStates);
    }

    private ToolBar buildToolBar(Stage primaryStage, Timeline timeline, Configuration simulationConfig) {
        ToolBar toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);
        Slider probabilitySlider = createSlider(simulationConfig.getProbCatch());
        Button buttonHome = createButton("Back to Main", null, BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(mySplashScene));
        Button buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        pauseGame(buttonPause, timeline);
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopGame(buttonStop, timeline);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage, timeline);
        toolBar.getItems().addAll(probabilitySlider, buttonHome, buttonPause, buttonStop, buttonUpload);
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
        return slider;
    }

    private void pauseGame(Button buttonPause, Timeline timeline) {
        buttonPause.setOnAction(e -> timeline.pause());
    }

    private void stopGame(Button buttonStop, Timeline timeline) {
        buttonStop.setOnAction(e -> timeline.stop());
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage, Timeline timeline) {
        buttonUpload.setOnAction(e -> {
            //if (timeline.getStatus() != Animation.Status.STOPPED) timeline.stop();
            try {
                start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.setScene(myAnimationScene);
        });
    }

    public static final String DATA_FILE_EXTENSION = "*.xml";
    // NOTE: generally accepted behavior that the chooser remembers where user left it last
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    public void start (Stage primaryStage) throws Exception {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        while (dataFile != null) {
            try {
                if(dataFile.getName().equals("fire.xml")){
//                        Pair<String, Game> p = new Pair<>(dataFile.getName(), new XMLParser("media").getGame(dataFile));
//                        showMessage(AlertType.INFORMATION, p.getSecond().toString());
                    //simulationConfig = new Fire();
                    new Reader("type").getFire(dataFile);
                }
                else if(dataFile.getName().equals("gameOfLife.xml")){
                    //simulationConfig = new Game();
                    new Reader("type").getGame(dataFile);
                }
                else if(dataFile.getName().equals("percolation.xml")){
                    //simulationConfig = new Percolation();
                    new Reader("type").getPercolation(dataFile);
                }
                else if(dataFile.getName().equals("prey.xml")){
                    //simulationConfig = new Prey();
                    new Reader("type").getPrey(dataFile);
                }
                else if(dataFile.getName().equals("segregation.xml")){
                    //simulationConfig = new Segregation();
                    new Reader("type").getSegregation(dataFile);
                }

            }
            catch (FileInputException e) {
                // handle error of unexpected file format
                showMessage(Alert.AlertType.ERROR, e.getMessage());
            }
            dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        }

        // nothing selected, so quit the application
        Platform.exit();
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

